package gc.garcol.totalorderingarchitecttimer.service;

import gc.garcol.totalorderingarchitecttimer.config.ConsumerVariables;
import gc.garcol.totalorderingarchitecttimer.model.Message;
import gc.garcol.totalorderingarchitecttimer.model.transport.*;
import gc.garcol.totalorderingarchitecttimer.repository.CommandLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jctools.queues.MpscLinkedQueue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author thaivc
 * @since 2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestHandler {

    static final int MAX_CONTINUOUS_ERROR = 20;
    static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    static       int continuousErrorCount = 0;
    private final MpscLinkedQueue<Message>     messageChannel;
    private final CommandLogRepository         commandLogRepository;
    private final OrderBookService             orderBookService;
    private final ConsumerVariables            consumerVariables;
    private final SnapshotService              snapshotService;
    private final LinkedHashMap<UUID, Message> inComingMessages = new LinkedHashMap<>();
    private long latestHandledIndex = -1;
    private long lastTickTime       = System.currentTimeMillis();

    public void init() {
        latestHandledIndex = snapshotService.getLatestSnapshotIndex();
        scheduledExecutorService.scheduleWithFixedDelay(this::runSafeDutyCircle, 0, 1, TimeUnit.MILLISECONDS);
    }

    public void runSafeDutyCircle() {
        try {
            dutyCircle();
            continuousErrorCount = 0;
        } catch (Exception e) {
            continuousErrorCount++;
            if (continuousErrorCount < MAX_CONTINUOUS_ERROR) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public void dutyCircle() {
        List<Command> commands = new ArrayList<>();
        for (int i = 0; i < consumerVariables.getBatchConsume(); i++) {
            Message message = messageChannel.poll();
            if (message == null) {
                break;
            }

            if (message.getRequest() instanceof Query query) {
                var result = orderBookService.handle(query);
                Thread.startVirtualThread(() -> message.getCallback().accept(result));
            } else if (message.getRequest() instanceof Command command) {
                commands.add(command);
                inComingMessages.put(message.getRequest().getCorrelationId(), message);
            }
        }

        if (!commands.isEmpty()) {
            commandLogRepository.persist(commands);
        }

        List<Command> persistedCommands = commandLogRepository.fetch(latestHandledIndex + 1,
                consumerVariables.getBatchHandle());

        for (Command command : persistedCommands) {
            RequestResult result = orderBookService.handle(command);

            if (inComingMessages.containsKey(command.getCorrelationId())) {
                while (true) {
                    Message message = inComingMessages.pollFirstEntry().getValue();
                    if (command.getCorrelationId().equals(message.getRequest().getCorrelationId())) {
                        if (message.getCallback() != null) {
                            Thread.startVirtualThread(() -> message.getCallback().accept(result));
                        }
                        break;
                    }
                }
            }
        }
        latestHandledIndex += persistedCommands.size();

        var now = System.currentTimeMillis();
        if (lastTickTime + consumerVariables.getTickIntervalMillis() < now) {
            lastTickTime = now;
            Message message = new Message();
            Request tickCommand = new CommandTick(now);
            message.setRequest(tickCommand);
            messageChannel.add(message);
        }
    }

}
