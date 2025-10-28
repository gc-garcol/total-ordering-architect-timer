package gc.garcol.totalorderingarchitecttimer.service;

import gc.garcol.totalorderingarchitecttimer.exception.LogicException;
import gc.garcol.totalorderingarchitecttimer.model.domain.*;
import gc.garcol.totalorderingarchitecttimer.model.transport.*;
import gc.garcol.totalorderingarchitecttimer.model.transport.dto.OrderDto;
import gc.garcol.totalorderingarchitecttimer.model.transport.dto.PostDto;
import gc.garcol.totalorderingarchitecttimer.repository.OrderBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author thaivc
 * @since 2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderBookService {

    private final OrderBookRepository orderBookRepository;

    public RequestResult handle(Query query) {
        try {
            switch (query) {
                case QueryPostById queryPostById -> {
                    return getPostById(queryPostById);
                }
                case QueryPosts queryPosts -> {
                    return getPosts(queryPosts);
                }
                case QueryOrders queryOrders -> {
                    return getOrders(queryOrders);
                }
                default -> {
                    return new RequestResult.Common(query.getCorrelationId(), 404, "query not found");
                }
            }
        } catch (LogicException logicException) {
            log.error(logicException.getMessage(), logicException);
            return new RequestResult.Error(query.getCorrelationId(), 400, logicException.getErrorMessage());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return new RequestResult.Error(query.getCorrelationId(), 500, exception.getMessage());
        }
    }

    public RequestResult handle(Command command) {
        try {
            switch (command) {
                case CommandPostCreate commandPostCreate -> {
                    return createPost(commandPostCreate);
                }
                case CommandOrderCreate commandOrderCreate -> {
                    return createOrder(commandOrderCreate);
                }
                case CommandTick commandTick -> {
                    return handleTicker(commandTick);
                }
                default -> {
                    return new RequestResult.Common(command.getCorrelationId(), 404, "command not found");
                }
            }
        } catch (LogicException logicException) {
            log.error("{}, {}", command, logicException.getMessage(), logicException);
            return new RequestResult.Error(command.getCorrelationId(), 400, logicException.getErrorMessage());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return new RequestResult.Error(command.getCorrelationId(), 500, exception.getMessage());
        }
    }

    private RequestResult handleTicker(CommandTick commandTick) {
        Map<OrderStatus, OrderStatus> nextStatusTimeout = Map.of(OrderStatus.OPEN, OrderStatus.CANCELED);

        // todo optimize: using HashedWheelTimer
        orderBookRepository.getOrders().values().stream()
                .filter(order -> order.getExpireTime() < commandTick.getCurrentTime()) //
                .forEach(order -> {
                    var currentStatus = order.getStatus();
                    order.setStatus(nextStatusTimeout.getOrDefault(order.getStatus(), currentStatus));
                });
        return new RequestResult.Common(commandTick.getCorrelationId(), HttpStatus.OK.value(),
                "on update tick success!!!");
    }

    private RequestResult createPost(CommandPostCreate commandPostCreate) {
        User user = orderBookRepository.findUserById(commandPostCreate.getUserId());
        if (user == null) {
            throw new LogicException("User not found");
        }

        if (user.getAmount().compareTo(commandPostCreate.getAmount()) < 0) {
            throw new LogicException("Balance not enough");
        }

        if (commandPostCreate.getAmount().compareTo(BigInteger.ZERO) <= 0) {
            throw new LogicException("Insufficient amount");
        }

        user.setAmount(user.getAmount().subtract(commandPostCreate.getAmount()));
        Post post = new Post();
        post.setAmount(commandPostCreate.getAmount());
        post.setId(orderBookRepository.getPostLastId() + 1);
        post.setOwnerId(user.getId());
        post.setType(PostType.BUY);
        orderBookRepository.store(post);

        return new CommandPostCreateResult(commandPostCreate.getCorrelationId(), HttpStatus.OK.value(), post.getId());
    }

    private RequestResult getPostById(QueryPostById queryPostById) {
        var post = orderBookRepository.findPostById(queryPostById.getPostId());
        if (post == null) {
            throw new LogicException("Post not found");
        }

        QueryPostByIdResult result = new QueryPostByIdResult();
        result.setCorrelationId(queryPostById.getCorrelationId());
        result.setPost(PostDto.from(post));
        return result;
    }

    private RequestResult getPosts(QueryPosts queryPosts) {
        Stream<Post> postStream = orderBookRepository.getPosts().values().stream();
        if (queryPosts.getMinPrice() != null) {
            postStream = postStream.filter(post -> post.getAmount().compareTo(queryPosts.getMinPrice()) >= 0);
        }

        if (queryPosts.getMaxPrice() != null) {
            postStream = postStream.filter(post -> post.getAmount().compareTo(queryPosts.getMaxPrice()) <= 0);
        }
//        postStream = postStream.sorted(Comparator.comparingLong(Post::getId).reversed());
        postStream = postStream.skip(queryPosts.getOffset()).limit(queryPosts.getLimit());
        List<PostDto> posts = postStream.map(PostDto::from).toList();
        QueryPostsResult result = new QueryPostsResult();
        result.setCorrelationId(queryPosts.getCorrelationId());
        result.setPosts(posts);
        result.setTotal(orderBookRepository.getPosts().size());
        return result;
    }

    private RequestResult createOrder(CommandOrderCreate commandOrderCreate) {
        User user = orderBookRepository.findUserById(commandOrderCreate.getOwnerId());
        if (user == null) {
            throw new LogicException("User not found");
        }

        Post post = orderBookRepository.findPostById(commandOrderCreate.getPostId());
        if (post == null) {
            throw new LogicException("Post not found");
        }

        if (user.getId().equals(post.getOwnerId())) {
            throw new LogicException("User is already own this post");
        }

        if (commandOrderCreate.getAmount().compareTo(BigInteger.ZERO) <= 0) {
            throw new LogicException("Insufficient amount");
        }

        if (commandOrderCreate.getAmount().compareTo(post.getAmount()) > 0) {
            throw new LogicException("Order amount is larger than post amount");
        }

        if (commandOrderCreate.getTimeoutSecond() <= 0) {
            throw new LogicException("Order timeout must be greater than 0");
        }

        Order order = new Order();
        order.setId(orderBookRepository.getOrderLastId() + 1);
        order.setAmount(commandOrderCreate.getAmount());
        order.setPostId(post.getId());
        order.setOwnerId(user.getId());
        order.setAmount(commandOrderCreate.getAmount());
        order.setStatus(OrderStatus.OPEN);
        order.setExpireTime(System.currentTimeMillis() + commandOrderCreate.getTimeoutSecond() * 1_000);
        orderBookRepository.store(order);

        CommandOrderCreateResult result = new CommandOrderCreateResult();
        result.setCorrelationId(commandOrderCreate.getCorrelationId());
        result.setCode(HttpStatus.OK.value());
        result.setOrderId(order.getId());
        return result;
    }

    private RequestResult getOrders(QueryOrders queryOrders) {
        List<OrderDto> orders = orderBookRepository.getOrders().values().stream()
                .sorted(Comparator.comparingLong(Order::getId).reversed()) //
                .skip(queryOrders.getOffset()) //
                .limit(queryOrders.getLimit()).map(OrderDto::from).toList();
        QueryOrdersResult result = new QueryOrdersResult();
        result.setCorrelationId(queryOrders.getCorrelationId());
        result.setOrders(orders);
        result.setTotal(orderBookRepository.getOrders().size());
        return result;
    }
}
