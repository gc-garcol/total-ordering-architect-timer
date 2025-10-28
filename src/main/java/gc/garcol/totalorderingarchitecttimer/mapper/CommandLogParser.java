package gc.garcol.totalorderingarchitecttimer.mapper;

import gc.garcol.totalorderingarchitecttimer.model.transport.*;

import java.nio.ByteBuffer;

/**
 * @author thaivc
 * @since 2025
 */
public interface CommandLogParser {

    static Command from(byte[] commandLog) {
        ByteBuffer buffer = ByteBuffer.wrap(commandLog);
        long id = buffer.getLong();
        int typeId = buffer.getInt();
        CommandType type = CommandType.fromId(typeId);
        byte[] remain = new byte[buffer.remaining()];
        buffer.get(remain);
        return from(type, remain);
    }

    static Command from(CommandType commandType, byte[] data) {
        switch (commandType) {
            case TICK:
                return CommandTick.fromBytes(data);
            case CREATE_POST:
                return CommandPostCreate.fromBytes(data);
            case CLOSE_POST:
                return CommandPostClose.fromBytes(data);
            case CREATE_ORDER:
                return CommandOrderCreate.fromBytes(data);
            case PAID_ORDER:
                return CommandOrderMarkAsPaid.fromBytes(data);
            case CANCELED_ORDER:
                return null;
        }
        return null;
    }
}
