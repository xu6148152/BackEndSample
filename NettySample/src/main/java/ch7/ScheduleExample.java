package ch7;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 9:54 PM
 */
public class ScheduleExample {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void schedule() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        ScheduledFuture<?> future = executor.schedule(() -> System.out.println("Now it is 60s later"), 60,
                                                      TimeUnit.SECONDS);
        executor.shutdown();
    }

    public static void scheduleViaEventLoop() {
        Channel ch = CHANNEL_FROM_SOMEWHERE;
        ScheduledFuture<?> future = ch.eventLoop().schedule(
                () -> System.out.println("60s later"), 60, TimeUnit.SECONDS);
    }

    public static void scheduleFixedViaEventLoop() {
        Channel ch = CHANNEL_FROM_SOMEWHERE;
        ScheduledFuture<?> future = ch.eventLoop().scheduleAtFixedRate(
                () -> System.out.println("Run every 60s"), 60, 60, TimeUnit.SECONDS);
    }

    public static void cancelingTaskUsingScheduledFuture() {
        Channel ch = CHANNEL_FROM_SOMEWHERE;
        io.netty.util.concurrent.ScheduledFuture<?> future = ch.eventLoop().scheduleAtFixedRate(
                () -> System.out.println("Run every 60s"), 60, 60, TimeUnit.SECONDS);
        boolean mayInterruptIfRunning = false;
        future.cancel(mayInterruptIfRunning);
    }
}
