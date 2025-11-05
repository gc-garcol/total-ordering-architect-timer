package gc.garcol.totalorderingarchitecttimer.controller.payload;

import gc.garcol.totalorderingarchitecttimer.model.transport.QueryOrders;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class QueryOrdersRequest {
    @Schema(description = "Number of records to skip for pagination", example = "0", defaultValue = "0")
    int offset;

    @Schema(description = "Maximum number of records to return", example = "20", defaultValue = "20")
    int limit;

    public QueryOrders toQuery() {
        QueryOrders queryOrders = new QueryOrders();
        queryOrders.setLimit(this.getLimit());
        queryOrders.setOffset(this.getOffset());
        return queryOrders;
    }
}
