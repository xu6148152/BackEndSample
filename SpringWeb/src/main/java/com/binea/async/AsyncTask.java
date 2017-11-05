package com.binea.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by binea
 * Date: 5/11/2017
 * TIME: 3:49 PM
 */
@Component
public class AsyncTask {
    public static Random random = new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        return executeTask("task1");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        return executeTask("task2");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        return executeTask("task3");
    }

    private AsyncResult<String> executeTask(String taskName) throws Exception {
        System.out.println("start doing " + taskName);
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("complete " + taskName + ", elapsed time: " + (end - start) + " ms");
        return new AsyncResult<>("task3 completed");
    }
}
