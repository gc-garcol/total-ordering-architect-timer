package gc.garcol.totalorderingarchitecttimer.controller.payload;

import gc.garcol.totalorderingarchitecttimer.model.transport.QueryPosts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Data
@Schema(description = "Request for querying posts with price range and pagination")
public class QueryPostsRequest {

    @Schema(description = "Minimum price filter", example = "100")
    Long minPrice;

    @Schema(description = "Maximum price filter", example = "1000")
    Long maxPrice;

    @Schema(description = "Number of records to skip for pagination", example = "0", defaultValue = "0")
    int offset;

    @Schema(description = "Maximum number of records to return", example = "20", defaultValue = "20")
    int limit;

    public QueryPosts toQuery() {
        QueryPosts queryPosts = new QueryPosts();
        if (this.minPrice != null) {
            queryPosts.setMinPrice(BigInteger.valueOf(this.minPrice));
        }
        if (this.maxPrice != null) {
            queryPosts.setMaxPrice(BigInteger.valueOf(this.maxPrice));
        }

        queryPosts.setLimit(limit);
        queryPosts.setOffset(offset);

        return queryPosts;
    }
}
