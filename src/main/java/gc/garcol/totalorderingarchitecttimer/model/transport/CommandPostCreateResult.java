package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandPostCreateResult implements RequestResult {
    private UUID correlationId;
    private int  code;
    private long postId;
}
