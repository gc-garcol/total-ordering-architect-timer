package gc.garcol.totalorderingarchitecttimer.controller.payload;

import gc.garcol.totalorderingarchitecttimer.model.transport.*;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
public interface RequestMapper {

    static CommandPostCreate toCommand(CreatePostRequest request) {
        return new CommandPostCreate(request.getUserId(), BigInteger.valueOf(request.getAmount()));
    }

    static QueryPostById toQueryPostById(Long postId) {
        return new QueryPostById(postId);
    }

    static QueryPosts toQueryPosts(QueryPostsRequest request) {
        QueryPosts queryPosts = new QueryPosts();
        if (request.getMinPrice() != null) {
            queryPosts.setMinPrice(BigInteger.valueOf(request.getMinPrice()));
        }
        if (request.getMaxPrice() != null) {
            queryPosts.setMaxPrice(BigInteger.valueOf(request.getMaxPrice()));
        }

        queryPosts.setLimit(request.getLimit());
        queryPosts.setOffset(request.getOffset());

        return queryPosts;
    }

    static CommandOrderCreate toCommand(CreateOrderRequest request) {
        return new CommandOrderCreate(request.postId, request.ownerId, request.getTimeoutSecond(),
                BigInteger.valueOf(request.getAmount()));
    }

    static QueryOrders toQueryOrders(QueryOrdersRequest request) {
        QueryOrders queryOrders = new QueryOrders();
        queryOrders.setLimit(request.getLimit());
        queryOrders.setOffset(request.getOffset());
        return queryOrders;
    }
}
