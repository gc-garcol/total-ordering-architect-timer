package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.Data;

import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class CommandOrderCreateResult implements RequestResult {
    private UUID correlationId;
    private int  code;
    private long orderId;
}
