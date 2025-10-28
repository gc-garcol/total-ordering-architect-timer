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
public class CommandTick implements Command {

    UUID   correlationId;
    Long   currentTime;
    byte[] bytes;

    public CommandTick(Long currentTime) {
        this.currentTime = currentTime;
        this.correlationId = UUID.randomUUID();
    }

    public CommandTick(UUID correlationId, Long currentTime) {
        this.currentTime = currentTime;
        this.correlationId = correlationId;
    }

    public static CommandTick fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        UUID correlationId = new UUID(buffer.getLong(), buffer.getLong());
        Long currentTime = buffer.getLong();
        return new CommandTick(correlationId, currentTime);
    }

    @Override
    public UUID getCorrelationId() {
        return correlationId;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.TICK;
    }

    @Override
    public byte[] toBytes() {
        if (bytes != null) {
            return bytes;
        }
        ByteBuffer buffer = ByteBuffer.allocate(2 * Long.BYTES + Long.BYTES);
        buffer.putLong(correlationId.getMostSignificantBits());
        buffer.putLong(correlationId.getLeastSignificantBits());
        buffer.putLong(currentTime);
        bytes = buffer.array();
        return bytes;
    }
}
