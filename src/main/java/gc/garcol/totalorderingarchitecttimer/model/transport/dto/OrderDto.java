package gc.garcol.totalorderingarchitecttimer.model.transport.dto;

import gc.garcol.totalorderingarchitecttimer.model.domain.Order;
import gc.garcol.totalorderingarchitecttimer.model.domain.OrderStatus;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class OrderDto {
    Long id;
    Long postId;
    Long ownerId;

    BigInteger amount;

    OrderStatus status;
    Long        expireTime;

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setPostId(order.getPostId());
        orderDto.setOwnerId(order.getBuyerId());
        orderDto.setAmount(order.getAmount());
        orderDto.setStatus(order.getStatus());
        orderDto.setExpireTime(order.getExpireTime());
        return orderDto;
    }
}
