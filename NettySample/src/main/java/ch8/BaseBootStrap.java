package ch8;

import io.netty.channel.ChannelFuture;

/**
 * Created by binea
 * Date: 27/1/2018
 * TIME: 4:04 PM
 */
public interface BaseBootStrap {
    default void handleChannelFuture(ChannelFuture future) {
        future.addListener(channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("Server bound");
            } else {
                System.err.println("Bind attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }
}
