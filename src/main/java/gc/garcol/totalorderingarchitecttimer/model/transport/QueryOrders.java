package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class QueryOrders implements Query {
    UUID correlationId = UUID.randomUUID();

    int offset;
    int limit;
}
