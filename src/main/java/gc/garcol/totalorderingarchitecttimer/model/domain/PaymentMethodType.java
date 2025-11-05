package gc.garcol.totalorderingarchitecttimer.model.domain;

/**
 * Payment method types supported in the P2P marketplace.
 *
 * @author thaivc
 * @since 2025
 */
public enum PaymentMethodType {
    /**
     * Bank wire transfer
     */
    BANK_TRANSFER,

    /**
     * PayPal account
     */
    PAYPAL,

    /**
     * Wise (formerly TransferWise)
     */
    WISE,

    /**
     * Revolut account
     */
    REVOLUT,

    /**
     * Cash App
     */
    CASH_APP,

    /**
     * Zelle
     */
    ZELLE,

    /**
     * Venmo
     */
    VENMO,

    /**
     * Other payment methods
     */
    OTHER
}
