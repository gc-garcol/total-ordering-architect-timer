package gc.garcol.totalorderingarchitecttimer.repository;

import gc.garcol.totalorderingarchitecttimer.model.transport.Command;

import java.util.List;

/**
 * @author thaiv
 * @since 2025
 */
public interface CommandLogRepository {
    void init();

    void persist(List<Command> commands);

    List<Command> fetch(Long from, int limit);
}
