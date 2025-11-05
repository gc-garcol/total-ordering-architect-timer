package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * User domain model for P2P cryptocurrency marketplace.
 *
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

    // User profile information
    String username;
    String email;
    String phoneNumber;

    // Verification status
    UserVerificationStatus verificationStatus = UserVerificationStatus.UNVERIFIED;

    // Balance information
    BigInteger cryptoBalance = BigInteger.ZERO;
    BigInteger fiatBalance   = BigInteger.ZERO;

    // Trading statistics
    BigDecimal rating          = BigDecimal.ZERO;  // Average rating (0.0 - 5.0)
    Integer    completedTrades = 0;
    Integer    cancelRate      = 0;  // Percentage of canceled orders

    // Timestamps
    LocalDateTime registrationDate;
    LocalDateTime lastActiveDate;

    // Status flags
    Boolean isActive = true;
    Boolean isBanned = false;

    public User(Long id, BigInteger amount) {
        this.id = id;
        this.cryptoBalance = amount;
        this.completedTrades = 0;
        this.cancelRate = 0;
        this.isActive = true;
        this.isBanned = false;
        this.registrationDate = LocalDateTime.now();
        this.lastActiveDate = LocalDateTime.now();
    }
}
