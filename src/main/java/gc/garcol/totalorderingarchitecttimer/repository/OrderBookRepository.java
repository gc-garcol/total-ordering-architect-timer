package gc.garcol.totalorderingarchitecttimer.repository;

import gc.garcol.totalorderingarchitecttimer.collection.TreeTimer;
import gc.garcol.totalorderingarchitecttimer.model.domain.Order;
import gc.garcol.totalorderingarchitecttimer.model.domain.Post;
import gc.garcol.totalorderingarchitecttimer.model.domain.User;
import lombok.Getter;
import org.agrona.collections.Long2ObjectHashMap;
import org.springframework.stereotype.Repository;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Repository
public class OrderBookRepository {

    Long2ObjectHashMap<User>  users  = new Long2ObjectHashMap<>();
    Long2ObjectHashMap<Post>  posts  = new Long2ObjectHashMap<>();
    Long2ObjectHashMap<Order> orders = new Long2ObjectHashMap<>();

    TreeTimer orderTimer = new TreeTimer("order-timer");

    long userLastId  = 0;
    long postLastId  = 0;
    long orderLastId = 0;

    public void store(User user) {
        users.put(user.getId(), user);
        userLastId = user.getId();
    }

    public void store(Post post) {
        posts.put(post.getId(), post);
        postLastId = post.getId();
    }

    public void store(Order order) {
        orders.put(order.getId(), order);
        posts.get(order.getPostId()).getOrders().add(order);
        orderLastId = order.getId();

        orderTimer.add(order.getId(), order.getExpireTime());
    }

    public User findUserById(Long id) {
        return users.get(id);
    }

    public Post findPostById(Long id) {
        return posts.get(id);
    }

    public Order findOrderById(Long id) {
        return orders.get(id);
    }

    public void removeOrder(Long id) {
        Order order = orders.remove(id);
        posts.get(id).getOrders().remove(order);
    }

    public void removePost(Long id) {
        Post post = posts.remove(id);
        post.getOrders().remove(orders.get(id));
    }
}
