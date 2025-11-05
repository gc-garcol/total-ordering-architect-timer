package gc.garcol.totalorderingarchitecttimer.model.domain;

/**
 * User verification status levels for P2P marketplace.
 *
 * @author thaivc
 * @since 2025
 */
public enum UserVerificationStatus {
    /**
     * No verification completed
     */
    UNVERIFIED,

    /**
     * Phone number verified
     */
    PHONE_VERIFIED,

    /**
     * Email address verified
     */
    EMAIL_VERIFIED,

    /**
     * KYC documents submitted, pending review
     */
    KYC_PENDING,

    /**
     * KYC approved and verified
     */
    KYC_VERIFIED,

    /**
     * High-volume merchant with enhanced verification
     */
    VERIFIED_MERCHANT
}

