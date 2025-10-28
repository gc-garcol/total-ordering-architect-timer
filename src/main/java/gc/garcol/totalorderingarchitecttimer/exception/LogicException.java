package gc.garcol.totalorderingarchitecttimer.exception;

import lombok.Getter;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
public class LogicException extends RuntimeException {
    private final String errorMessage;

    public LogicException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
