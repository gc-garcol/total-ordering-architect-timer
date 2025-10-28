package gc.garcol.totalorderingarchitecttimer.model.transport;

import java.util.UUID;

/**
 * @author thaivc
 * @since 2025
 */
public interface RequestResult {

    record Common(UUID correlationId, int code, String message) implements RequestResult {
    }

    record Error(UUID correlationId, int code, String message) implements RequestResult {
    }
}
