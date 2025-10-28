package gc.garcol.totalorderingarchitecttimer.controller.payload;

import lombok.Data;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class CreateOrderRequest {
    Long    postId;
    Long    ownerId;
    Long    amount;
    Integer timeoutSecond;
}
