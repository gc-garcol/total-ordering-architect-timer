package gc.garcol.totalorderingarchitecttimer.model.domain;

/**
 * Post status for P2P marketplace posts.
 * 
 * @author thaivc
 * @since 2025
 */
public enum PostStatus {
    /**
     * Post is live and accepting orders
     */
    ACTIVE,
    
    /**
     * Temporarily paused by user
     */
    PAUSED,
    
    /**
     * Closed by user or system
     */
    CLOSED,
    
    /**
     * Auto-closed due to expiration
     */
    EXPIRED
}

