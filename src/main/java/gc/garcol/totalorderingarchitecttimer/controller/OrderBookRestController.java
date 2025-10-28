package gc.garcol.totalorderingarchitecttimer.controller;

import gc.garcol.totalorderingarchitecttimer.controller.payload.*;
import gc.garcol.totalorderingarchitecttimer.model.transport.*;
import gc.garcol.totalorderingarchitecttimer.service.RequestPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * @author thaivc
 * @since 2025
 */
@RestController
@RequestMapping("/api/order-book")
@RequiredArgsConstructor
public class OrderBookRestController {

    private final RequestPublisher requestPublisher;

    @PostMapping("/buy-post")
    CompletableFuture<RequestResult> createPost(@RequestBody CreatePostRequest request) {
        CommandPostCreate command = RequestMapper.toCommand(request);
        return requestPublisher.publishRequest(command);
    }

    @GetMapping("/buy-post/{id}")
    CompletableFuture<RequestResult> getPostById(@PathVariable("id") Long id) {
        QueryPostById queryPostById = RequestMapper.toQueryPostById(id);
        return requestPublisher.publishRequest(queryPostById);
    }

    @GetMapping("/buy-post")
    CompletableFuture<RequestResult> getPosts(QueryPostsRequest request) {
        QueryPosts queryPosts = RequestMapper.toQueryPosts(request);
        return requestPublisher.publishRequest(queryPosts);
    }

    @PostMapping("/buy-order")
    CompletableFuture<RequestResult> create(@RequestBody CreateOrderRequest request) {
        CommandOrderCreate command = RequestMapper.toCommand(request);
        return requestPublisher.publishRequest(command);
    }

    @GetMapping("/buy-order")
    CompletableFuture<RequestResult> getOrders(QueryOrdersRequest request) {
        QueryOrders queryOrders = RequestMapper.toQueryOrders(request);
        return requestPublisher.publishRequest(queryOrders);
    }

}
