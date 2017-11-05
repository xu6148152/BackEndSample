package com.binea.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by binea
 * Date: 5/11/2017
 * TIME: 3:42 PM
 */
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void repeatCurrentTime() {
        System.out.println("current time: " + sdf.format(new Date()));
    }
}
