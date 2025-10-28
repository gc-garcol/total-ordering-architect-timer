package gc.garcol.totalorderingarchitecttimer.model;

import gc.garcol.totalorderingarchitecttimer.model.transport.Request;
import gc.garcol.totalorderingarchitecttimer.model.transport.RequestResult;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;

/**
 * @author thaivc
 * @since 2025
 */
@Getter
@Setter
public class Message {
    Request                 request;
    Consumer<RequestResult> callback;
}
