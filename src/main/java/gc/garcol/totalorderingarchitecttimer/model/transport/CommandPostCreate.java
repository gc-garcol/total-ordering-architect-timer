package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@ToString
@NoArgsConstructor
public class CommandPostCreate implements Command {
    UUID       correlationId;
    Long       issueAt;
    Long       userId;
    BigInteger amount;

    byte[] bytes;

    public CommandPostCreate(Long userId, BigInteger amount) {
        this.userId = userId;
        this.amount = amount;
        this.correlationId = UUID.randomUUID();
    }

    public CommandPostCreate(UUID correlationId, Long userId, BigInteger amount) {
        this.userId = userId;
        this.amount = amount;
        this.correlationId = correlationId;
    }

    public static CommandPostCreate fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        byte[] amountBytes = new byte[bytes.length - 2 * Long.BYTES - Long.BYTES * 2];
        UUID correlationId = new UUID(buffer.getLong(), buffer.getLong());
        Long issueAt = buffer.getLong();
        Long userId = buffer.getLong();
        buffer.get(amountBytes);
        BigInteger amount = new BigInteger(amountBytes);
        CommandPostCreate command = new CommandPostCreate(correlationId, userId, amount);
        command.issueAt = issueAt;
        return command;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_POST;
    }

    @Override
    public byte[] toBytes() {
        if (bytes != null) {
            return bytes;
        }
        byte[] amountBytes = amount.toByteArray();
        ByteBuffer buffer = ByteBuffer.allocate(2 * Long.BYTES + Long.BYTES + Long.BYTES + amountBytes.length);
        buffer.putLong(correlationId.getMostSignificantBits());
        buffer.putLong(correlationId.getLeastSignificantBits());
        buffer.putLong(issueAt);
        buffer.putLong(userId);
        buffer.put(amountBytes);
        bytes = buffer.array();
        return bytes;
    }
}
