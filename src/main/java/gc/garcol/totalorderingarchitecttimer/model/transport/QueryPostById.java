package gc.garcol.totalorderingarchitecttimer.model.transport;

import lombok.Data;

import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class QueryPostById implements Query {
    UUID correlationId;
    long postId;

    public QueryPostById(long postId) {
        this.postId = postId;
        this.correlationId = UUID.randomUUID();
    }
}
