package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Post domain model for P2P cryptocurrency marketplace.
 * Represents a buy or sell advertisement for cryptocurrency trading.
 *
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

    // Amount information
    BigInteger amount;              // Total crypto amount (deprecated, use totalAmount)
    BigInteger totalAmount;         // Total crypto available
    BigInteger availableAmount;      // Remaining crypto available

    // Price information
    BigInteger price;               // Price per unit in quote currency
    BigInteger minQuote;             // Minimum quote (deprecated, use minTradeAmount)
    BigInteger maxQuote;             // Maximum quote (deprecated, use maxTradeAmount)
    BigInteger minTradeAmount;       // Minimum order amount
    BigInteger maxTradeAmount;       // Maximum order amount

    // Currency information
    String baseCurrency = "BTC";
    String quoteCurrency = "USD";

    // Payment and trading terms
    Set<PaymentMethodType> acceptedPaymentMethods = new HashSet<>();
    String terms;                   // Trading terms and conditions
    Integer paymentTimeLimit;       // Minutes to complete payment
    Integer autoReleaseTime;        // Minutes for auto-release after payment

    // Status
    PostStatus status = PostStatus.ACTIVE;

    // Statistics
    Integer viewCount = 0;
    Integer orderCount = 0;

    // Timestamps
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime expiresAt;

    // Orders associated with this post
    Set<Order> orders = new HashSet<>();
}
