package gc.garcol.totalorderingarchitecttimer.repository;

import gc.garcol.totalorderingarchitecttimer.config.RocksDBVariables;
import gc.garcol.totalorderingarchitecttimer.mapper.CommandLogParser;
import gc.garcol.totalorderingarchitecttimer.model.transport.Command;
import gc.garcol.totalorderingarchitecttimer.model.transport.CommandLog;
import gc.garcol.totalorderingarchitecttimer.util.ByteUtils;
import lombok.SneakyThrows;
import org.rocksdb.*;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author thaivc
 * @since 2025
 */
@Repository
public class CommandLogRockDBRepositoryImpl implements CommandLogRepository {

    private final RocksDBVariables rocksDBVariables;
    private final byte[]             LATEST_INDEX_KEY = "LATEST_INDEX_KEY".getBytes();
    private       RocksDB            rocksDB;
    private       ColumnFamilyHandle commandLogHandle;
    private       ColumnFamilyHandle latestCommandLogHandle;
    private       Long               latestIndex      = -1L;

    public CommandLogRockDBRepositoryImpl(RocksDBVariables rocksDBVariables) {
        this.rocksDBVariables = rocksDBVariables;
    }

    @SneakyThrows
    @Override
    public void init() {
        String commandLogPath = rocksDBVariables.getPath() + File.pathSeparator + "commandLog";
        final File dbDir = new File(commandLogPath);

        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        var descriptors = List.of(new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, new ColumnFamilyOptions()),
                new ColumnFamilyDescriptor("commandLog".getBytes(), new ColumnFamilyOptions()),
                new ColumnFamilyDescriptor("latestIndex".getBytes(), new ColumnFamilyOptions()));

        List<ColumnFamilyHandle> cfHandles = new ArrayList<>();
        DBOptions dbOptions = new DBOptions().setCreateIfMissing(true).setCreateMissingColumnFamilies(true);
        rocksDB = RocksDB.open(dbOptions, commandLogPath, descriptors, cfHandles);

        commandLogHandle = cfHandles.get(1);
        latestCommandLogHandle = cfHandles.get(2);

        latestIndex = Optional.ofNullable(rocksDB.get(latestCommandLogHandle, LATEST_INDEX_KEY)).map(ByteUtils::toLong)
                .orElse(-1L);
    }

    @Override
    public void persist(List<Command> commands) {
        try (WriteBatch writeBatch = new WriteBatch()) {
            boolean hasRecord = false;
            long currentLatestIndex = this.latestIndex;
            for (Command command : commands) {
                hasRecord = true;
                CommandLog commandLog = new CommandLog();
                commandLog.setType(command.getCommandType());
                commandLog.setId(++currentLatestIndex);
                commandLog.setCommand(command);
                writeBatch.put(commandLogHandle, ByteUtils.fromLong(commandLog.getId()), commandLog.toBytes());
            }
            if (hasRecord) {
                writeBatch.put(latestCommandLogHandle, LATEST_INDEX_KEY, ByteUtils.fromLong(currentLatestIndex));
                rocksDB.write(new WriteOptions(), writeBatch);
                this.latestIndex = currentLatestIndex;
            }
        } catch (RocksDBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SneakyThrows
    public List<Command> fetch(Long fromIndex, int limit) {
        if (fromIndex > latestIndex) {
            return Collections.emptyList();
        }

        long toIndex = fromIndex + limit - 1;
        long upperIndex = Long.min(toIndex, latestIndex);
        int size = (int) (upperIndex - fromIndex + 1);

        if (size <= 0) {
            return Collections.emptyList();
        }

        List<byte[]> keys = new ArrayList<>(size);
        for (long key = fromIndex; key <= upperIndex; key++) {
            keys.add(ByteUtils.fromLong(key));
        }
        List<ColumnFamilyHandle> cfHandles = new ArrayList<>(size);
        for (int i = 0; i < keys.size(); i++) {
            cfHandles.add(commandLogHandle);
        }
        List<byte[]> values = rocksDB.multiGetAsList(cfHandles, keys);
        return values.stream().map(CommandLogParser::from).toList();
    }
}
