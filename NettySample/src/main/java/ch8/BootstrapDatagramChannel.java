package ch8;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.OioDatagramChannel;

import java.net.InetSocketAddress;

/**
 * Created by binea
 * Date: 27/1/2018
 * TIME: 3:14 PM
 */
public class BootstrapDatagramChannel {
    public void bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new OioEventLoopGroup()).channel(OioDatagramChannel.class).handler(
                new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

                    }
                });
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(0));
        future.addListener(channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("Channel bound");
            } else {
                System.err.print("Bind attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }
}
