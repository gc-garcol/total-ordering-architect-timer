package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.*;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @EqualsAndHashCode.Include
    Long id;

    BigInteger amount;
}
