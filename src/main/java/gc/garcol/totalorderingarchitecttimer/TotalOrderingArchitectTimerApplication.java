package gc.garcol.totalorderingarchitecttimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class TotalOrderingArchitectTimerApplication {

    static void main(String[] args) {
        SpringApplication.run(TotalOrderingArchitectTimerApplication.class, args);
    }

}
