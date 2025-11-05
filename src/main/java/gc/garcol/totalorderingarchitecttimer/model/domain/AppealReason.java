package gc.garcol.totalorderingarchitecttimer.model.domain;

/**
 * Reasons for opening an appeal/dispute in the P2P marketplace.
 * 
 * @author thaivc
 * @since 2025
 */
public enum AppealReason {
    /**
     * Seller claims payment not received
     */
    PAYMENT_NOT_RECEIVED,
    
    /**
     * Buyer claims payment not confirmed
     */
    PAYMENT_NOT_CONFIRMED,
    
    /**
     * Payment amount mismatch
     */
    WRONG_AMOUNT,
    
    /**
     * Suspected fraud
     */
    FRAUDULENT,
    
    /**
     * Other issues
     */
    OTHER
}

