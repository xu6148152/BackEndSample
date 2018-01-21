package ch6;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 3:25 PM
 */
public class ChannelFutures {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
    private static final ByteBuf SOME_MSG_FROM_SOMEWHERE = Unpooled.buffer(1024);

    public static void addingChannelFutureListener() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        ByteBuf someMessage = SOME_MSG_FROM_SOMEWHERE;

        ChannelFuture future = channel.write(someMessage);
        future.addListener((ChannelFutureListener) future1 -> {
            if (!future1.isSuccess()) {
                future1.cause().printStackTrace();
                future1.channel().closeFuture();
            }
        });
    }
}
