package gc.garcol.totalorderingarchitecttimer.service;

import gc.garcol.totalorderingarchitecttimer.model.Message;
import gc.garcol.totalorderingarchitecttimer.model.transport.Request;
import gc.garcol.totalorderingarchitecttimer.model.transport.RequestResult;
import lombok.RequiredArgsConstructor;
import org.jctools.queues.MpscLinkedQueue;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author thaivc
 * @since 2025
 */
@Service
@RequiredArgsConstructor
public class RequestPublisher {

    private final MpscLinkedQueue<Message> messageChannel;

    public CompletableFuture<RequestResult> publishRequest(Request request) {
        CompletableFuture<RequestResult> future = new CompletableFuture<>();
        future.completeOnTimeout(
                new RequestResult.Common(request.getCorrelationId(), HttpStatus.GATEWAY_TIMEOUT.value(), "timeout"), 10,
                TimeUnit.SECONDS);
        Message message = new Message();
        message.setRequest(request);
        message.setCallback(future::complete);
        messageChannel.add(message);
        return future;
    }

}
