package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Order domain model for P2P cryptocurrency marketplace.
 * Represents an order created by a buyer against a seller's post.
 *
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

    // User information
    Long buyerId;        // User creating the order
    Long sellerId;      // Post owner (seller)

    // Amount and price information
    BigInteger amount;       // Crypto amount
    BigInteger price;        // Price per unit at order time
    BigInteger totalPrice;   // Total fiat amount (amount * price)

    // Status
    OrderStatus status;
    Long        expireTime;         // Auto-cancel deadline (milliseconds since epoch)

    // Payment information
    PaymentMethodType paymentMethod;
    Long              paymentMethodId;
    String            paymentReference;  // Payment transaction reference
    String            paymentProof;      // URL or reference to payment proof

    // Timestamps
    LocalDateTime createdAt;
    LocalDateTime paidAt;         // When buyer marks as paid
    LocalDateTime confirmedAt;    // When seller confirms payment
    LocalDateTime completedAt;
    LocalDateTime canceledAt;

    // Appeal and cancellation
    Long   appealId;          // If order is in dispute
    String cancelReason;
    String notes;           // Additional notes from buyer/seller
}
