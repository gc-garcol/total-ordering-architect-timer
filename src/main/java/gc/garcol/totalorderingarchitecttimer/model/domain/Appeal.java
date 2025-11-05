package gc.garcol.totalorderingarchitecttimer.model.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Appeal/dispute domain model for P2P cryptocurrency marketplace.
 * Represents a dispute raised by a user regarding an order.
 * 
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Appeal {

    @EqualsAndHashCode.Include
    Long id;

    /**
     * Order ID associated with this appeal
     */
    Long orderId;

    /**
     * User ID who initiated the appeal
     */
    Long initiatorId;

    /**
     * Reason for the appeal
     */
    AppealReason reason;

    /**
     * Description of the issue
     */
    String description;

    /**
     * URLs or references to evidence (screenshots, documents)
     */
    List<String> evidenceUrls = new ArrayList<>();

    /**
     * Current status of the appeal
     */
    AppealStatus status = AppealStatus.PENDING;

    /**
     * Admin ID assigned to review this appeal (null if not assigned)
     */
    Long assignedAdminId;

    /**
     * Resolution provided by admin
     */
    String adminResolution;

    /**
     * When the appeal was created
     */
    LocalDateTime createdAt;

    /**
     * When the appeal was resolved (null if not resolved)
     */
    LocalDateTime resolvedAt;
}

