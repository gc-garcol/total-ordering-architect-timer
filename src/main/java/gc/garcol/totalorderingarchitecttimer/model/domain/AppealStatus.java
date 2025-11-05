package gc.garcol.totalorderingarchitecttimer.model.domain;

/**
 * Appeal/dispute status in the P2P marketplace.
 *
 * @author thaivc
 * @since 2025
 */
public enum AppealStatus {
    /**
     * Appeal created, pending review
     */
    PENDING,

    /**
     * Appeal is currently under review by admin
     */
    IN_REVIEW,

    /**
     * Appeal has been resolved
     */
    RESOLVED,

    /**
     * Appeal has been closed
     */
    CLOSED
}

