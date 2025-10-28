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
@ConfigurationProperties(prefix = "rocksdb")
public class RocksDBVariables {
    public String path;
}
