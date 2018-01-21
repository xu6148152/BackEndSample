package ch7;

import java.util.Collections;
import java.util.List;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 9:51 PM
 */
public class EventLoopExample {

    public static void executeTaskInEventLoop() {
        boolean terminated = true;
        while (!terminated) {
            List<Runnable> readyEvents = blockUntilEventsReady();
            readyEvents.forEach(ev -> ev.run());
        }
    }

    private static final List<Runnable> blockUntilEventsReady() {
        return Collections.singletonList(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
