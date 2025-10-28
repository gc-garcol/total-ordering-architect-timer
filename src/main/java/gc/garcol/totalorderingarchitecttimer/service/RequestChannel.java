package gc.garcol.totalorderingarchitecttimer.service;

import gc.garcol.totalorderingarchitecttimer.model.Message;
import org.jctools.queues.MpscLinkedQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author thaivc
 * @since 2025
 */
@Configuration
public class RequestChannel {

    @Bean
    MpscLinkedQueue<Message> messageChannel() {
        return new MpscLinkedQueue<>();
    }
}
