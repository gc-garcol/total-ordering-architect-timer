package gc.garcol.totalorderingarchitecttimer.controller.payload;

import gc.garcol.totalorderingarchitecttimer.model.transport.CommandPostCreate;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Data
public class CreatePostRequest {
    private Long userId;
    private Long amount;

    public CommandPostCreate toCommand() {
        return new CommandPostCreate(this.getUserId(), BigInteger.valueOf(this.getAmount()));
    }
}
