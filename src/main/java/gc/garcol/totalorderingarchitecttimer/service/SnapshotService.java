package gc.garcol.totalorderingarchitecttimer.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Service
public class SnapshotService {

    long latestSnapshotIndex = -1;

    public void doSnapshot() {
        // todo ...
    }
}
