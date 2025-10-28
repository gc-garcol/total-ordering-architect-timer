package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CommandLog {

    @EqualsAndHashCode.Include
    Long id;

    CommandType type;

    Command command;

    private byte[] bytes;

    public byte[] toBytes() {
        if (bytes != null) {
            return bytes;
        }

        byte[] commandLogBytes = command.toBytes();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES + Integer.BYTES + commandLogBytes.length);
        buffer.putLong(id);
        buffer.putInt(type.id);
        buffer.put(commandLogBytes);
        bytes = buffer.array();
        return bytes;
    }

}
