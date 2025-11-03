package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @EqualsAndHashCode.Include
    Long id;
    Long ownerId;

    PostType type = PostType.BUY;

    BigInteger amount;
    BigInteger price;

    BigInteger minQuote;
    BigInteger maxQuote;

    String     baseCurrency  = "BTC";
    String     quoteCurrency = "USD";

    Set<Order> orders = new HashSet<>();
}
