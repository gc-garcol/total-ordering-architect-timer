package gc.garcol.totalorderingarchitecttimer.model.transport;

/**
 * @author thaivc
 * @since 2025
 */
public interface Command extends Request {
    CommandType getCommandType();

    Long getIssueAt();

    byte[] toBytes();
}
