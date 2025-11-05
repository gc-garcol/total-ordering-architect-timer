package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Payment method domain model for P2P cryptocurrency marketplace.
 * Represents a payment method associated with a user account.
 *
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentMethod {

    @EqualsAndHashCode.Include
    Long id;

    /**
     * User ID that owns this payment method
     */
    Long userId;

    /**
     * Type of payment method
     */
    PaymentMethodType type;

    /**
     * Account holder name
     */
    String accountName;

    /**
     * Account number or identifier
     */
    String accountNumber;

    /**
     * Bank name (for bank transfers)
     */
    String bankName;

    /**
     * Branch name (for bank transfers)
     */
    String branchName;

    /**
     * Additional information stored as JSON string for flexible data
     */
    String additionalInfo;

    /**
     * Whether this payment method is currently active
     */
    Boolean isActive = true;

    /**
     * When this payment method was created
     */
    LocalDateTime createdAt;

    /**
     * When this payment method was verified (null if not verified)
     */
    LocalDateTime verifiedAt;
}
