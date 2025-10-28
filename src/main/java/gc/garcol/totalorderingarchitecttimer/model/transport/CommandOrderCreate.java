package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@NoArgsConstructor
public class CommandOrderCreate implements Command {
    UUID correlationId;

    Long       postId;
    Long       ownerId;
    Integer    timeoutSecond;
    BigInteger amount;

    byte[] bytes;

    public CommandOrderCreate(Long postId, Long ownerId, Integer timeout, BigInteger amount) {
        this.postId = postId;
        this.ownerId = ownerId;
        this.timeoutSecond = timeout;
        this.amount = amount;
        this.correlationId = UUID.randomUUID();
    }

    public CommandOrderCreate(UUID correlationId, Long postId, Long ownerId, Integer timeout, BigInteger amount) {
        this.correlationId = correlationId;
        this.postId = postId;
        this.ownerId = ownerId;
        this.timeoutSecond = timeout;
        this.amount = amount;
    }

    public static CommandOrderCreate fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        byte[] amountBytes = new byte[bytes.length - Long.BYTES * 2 - Long.BYTES * 2 - Integer.BYTES];
        UUID correlationId = new UUID(buffer.getLong(), buffer.getLong());
        Long postId = buffer.getLong();
        Long ownerId = buffer.getLong();
        Integer timeout = buffer.getInt();
        buffer.get(amountBytes);
        BigInteger amount = new BigInteger(amountBytes);
        return new CommandOrderCreate(correlationId, postId, ownerId, timeout, amount);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_ORDER;
    }

    @Override
    public byte[] toBytes() {
        if (bytes != null) {
            return bytes;
        }
        byte[] amountBytes = amount.toByteArray();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * 2 + Long.BYTES * 2 + Integer.BYTES + amountBytes.length);
        buffer.putLong(correlationId.getMostSignificantBits());
        buffer.putLong(correlationId.getLeastSignificantBits());
        buffer.putLong(postId);
        buffer.putLong(ownerId);
        buffer.putInt(timeoutSecond);
        buffer.put(amountBytes);
        bytes = buffer.array();
        return bytes;
    }
}
