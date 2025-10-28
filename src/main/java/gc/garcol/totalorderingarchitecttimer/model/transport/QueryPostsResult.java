package gc.garcol.totalorderingarchitecttimer.model.transport;

import gc.garcol.totalorderingarchitecttimer.model.transport.dto.PostDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class QueryPostsResult implements RequestResult {
    UUID          correlationId;
    int           total;
    List<PostDto> posts;
}
