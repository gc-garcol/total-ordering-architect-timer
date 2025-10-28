package gc.garcol.totalorderingarchitecttimer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "queue.handler")
public class ConsumerVariables {
    int batchConsume;
    int batchHandle;
    int tickIntervalMillis;
}
