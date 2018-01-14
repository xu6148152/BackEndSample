package ch1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by binea
 * Date: 13/1/2018
 * TIME: 4:08 PM
 */
public class ConnectExample {

    public static void connect(String address, int port) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).handler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ConnectHandler());
            }
        }).channel(NioSocketChannel.class).
                connect(new InetSocketAddress(address, port)).addListener(
                new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                            ChannelFuture wf = future.channel().writeAndFlush(buffer);
                        } else {
                            Throwable cause = future.cause();
                            cause.printStackTrace();
                        }
                    }
                });
    }
}
