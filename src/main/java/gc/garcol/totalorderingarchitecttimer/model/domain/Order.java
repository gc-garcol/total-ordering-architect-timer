package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @EqualsAndHashCode.Include
    Long id;
    Long postId;
    Long ownerId;

    BigInteger amount;

    OrderStatus status;
    Long        expireTime;
}
