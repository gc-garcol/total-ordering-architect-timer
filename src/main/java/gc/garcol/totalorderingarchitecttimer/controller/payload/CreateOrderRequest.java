package gc.garcol.totalorderingarchitecttimer.controller.payload;

import gc.garcol.totalorderingarchitecttimer.model.transport.CommandOrderCreate;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class CreateOrderRequest {
    Long postId;
    Long ownerId;
    Long amount;
    Integer timeoutSecond;

    public CommandOrderCreate toCommand() {
        return new CommandOrderCreate(this.postId, this.ownerId, this.timeoutSecond,
                BigInteger.valueOf(this.amount));
    }
}
