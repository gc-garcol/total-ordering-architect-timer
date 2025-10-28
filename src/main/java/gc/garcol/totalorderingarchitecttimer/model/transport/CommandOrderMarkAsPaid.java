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
public class CommandOrderMarkAsPaid implements Command {
    UUID   correlationId;
    Long   id;
    byte[] bytes;

    public CommandOrderMarkAsPaid(Long id) {
        this.id = id;
        this.correlationId = UUID.randomUUID();
    }

    public CommandOrderMarkAsPaid(UUID correlationId, Long id) {
        this.id = id;
        this.correlationId = correlationId;
    }

    public static CommandOrderMarkAsPaid fromBytes(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        UUID correlationId = new UUID(buffer.getLong(), buffer.getLong());
        Long id = buffer.getLong();
        return new CommandOrderMarkAsPaid(correlationId, id);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.PAID_ORDER;
    }

    @Override
    public byte[] toBytes() {
        if (bytes != null) {
            return bytes;
        }
        ByteBuffer buffer = ByteBuffer.allocate(2 * Long.BYTES + Long.BYTES);
        buffer.putLong(correlationId.getMostSignificantBits());
        buffer.putLong(correlationId.getLeastSignificantBits());
        buffer.putLong(id);
        bytes = buffer.array();
        return bytes;
    }
}
