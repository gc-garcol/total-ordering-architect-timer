package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.RequiredArgsConstructor;

/**
 * @author thaivc
 * @since 2025
 */
@RequiredArgsConstructor
public enum CommandType {

    TICK(10),

    CREATE_POST(100), CLOSE_POST(101),

    CREATE_ORDER(200), PAID_ORDER(201), APPEAL_ORDER(202), COMPLETE_ORDER(203), CANCELED_ORDER(204);

    public final int id;

    public static CommandType fromId(int id) {
        for (CommandType type : CommandType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
