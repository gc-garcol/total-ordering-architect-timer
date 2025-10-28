package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class QueryPosts implements Query {
    UUID correlationId = UUID.randomUUID();

    BigInteger minPrice;
    BigInteger maxPrice;

    int offset;
    int limit;
}
