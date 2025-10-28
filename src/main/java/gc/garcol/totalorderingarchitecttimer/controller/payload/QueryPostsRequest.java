package gc.garcol.totalorderingarchitecttimer.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

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
}
