package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@NoArgsConstructor
public class CommandPostClose implements Command {
    UUID   correlationId;
    Long   issueAt;
    Long   id;
    byte[] bytes;

    public CommandPostClose(Long id) {
        this.id = id;
        this.correlationId = UUID.randomUUID();
    }

    public CommandPostClose(UUID correlationId, Long id) {
        this.id = id;
        this.correlationId = correlationId;
    }

    public static CommandPostClose fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        UUID correlationId = new UUID(buffer.getLong(), buffer.getLong());
        long issueAt = buffer.getLong();
        Long id = buffer.getLong();
        CommandPostClose command = new CommandPostClose(correlationId, id);
        command.issueAt = issueAt;
        return command;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CLOSE_POST;
    }

    @Override
    public byte[] toBytes() {
        if (bytes != null) {
            return bytes;
        }
        ByteBuffer buffer = ByteBuffer.allocate(2 * Long.BYTES + Long.BYTES + Long.BYTES);
        buffer.putLong(correlationId.getMostSignificantBits());
        buffer.putLong(correlationId.getLeastSignificantBits());
        buffer.putLong(issueAt);
        buffer.putLong(id);
        bytes = buffer.array();
        return bytes;
    }
}
