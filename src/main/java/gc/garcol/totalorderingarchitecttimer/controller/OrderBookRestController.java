package gc.garcol.totalorderingarchitecttimer.controller;

import gc.garcol.totalorderingarchitecttimer.controller.payload.CreateOrderRequest;
import gc.garcol.totalorderingarchitecttimer.controller.payload.CreatePostRequest;
import gc.garcol.totalorderingarchitecttimer.controller.payload.QueryOrdersRequest;
import gc.garcol.totalorderingarchitecttimer.controller.payload.QueryPostsRequest;
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
        CommandPostCreate command = request.toCommand();
        return requestPublisher.publishRequest(command);
    }

    @GetMapping("/buy-post/{id}")
    CompletableFuture<RequestResult> getPostById(@PathVariable("id") Long id) {
        QueryPostById queryPostById = new QueryPostById(id);
        return requestPublisher.publishRequest(queryPostById);
    }

    @GetMapping("/buy-post")
    CompletableFuture<RequestResult> getPosts(QueryPostsRequest request) {
        QueryPosts queryPosts = request.toQuery();
        return requestPublisher.publishRequest(queryPosts);
    }

    @PostMapping("/buy-order")
    CompletableFuture<RequestResult> create(@RequestBody CreateOrderRequest request) {
        CommandOrderCreate command = request.toCommand();
        return requestPublisher.publishRequest(command);
    }

    @GetMapping("/buy-order")
    CompletableFuture<RequestResult> getOrders(QueryOrdersRequest request) {
        QueryOrders queryOrders = request.toQuery();
        return requestPublisher.publishRequest(queryOrders);
    }

}
