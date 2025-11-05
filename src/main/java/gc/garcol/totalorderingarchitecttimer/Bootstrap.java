package gc.garcol.totalorderingarchitecttimer;

import gc.garcol.totalorderingarchitecttimer.model.domain.Post;
import gc.garcol.totalorderingarchitecttimer.model.domain.PostType;
import gc.garcol.totalorderingarchitecttimer.model.domain.User;
import gc.garcol.totalorderingarchitecttimer.repository.CommandLogRepository;
import gc.garcol.totalorderingarchitecttimer.repository.OrderBookRepository;
import gc.garcol.totalorderingarchitecttimer.service.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rocksdb.RocksDB;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @author thaivc
 * @since 2025
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap {

    private final OrderBookRepository  orderBookRepository;
    private final CommandLogRepository cmdLogRepository;
    private final RequestHandler       requestHandler;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("Seek data");
        orderBookRepository.store(new User(100L, BigInteger.valueOf(9_000_000_000_000_000_000L)));
        orderBookRepository.store(new User(200L, BigInteger.valueOf(9_000_000_000_000_000_000L)));

        Post post = new Post();
        post.setId(1L);
        post.setOwnerId(100L);
        post.setAvailableAmount(BigInteger.valueOf(1_000L));
        post.setType(PostType.BUY);
        post.setPrice(BigInteger.valueOf(101_000L));
        post.setMinQuote(BigInteger.valueOf(300L));
        post.setMaxQuote(BigInteger.valueOf(500L));
        orderBookRepository.store(post);

        log.info("Init rocksDB");
        RocksDB.loadLibrary();
        cmdLogRepository.init();

        requestHandler.init();
        log.info("Bootstrap started");
    }
}
