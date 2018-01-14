import ch1.ConnectHandler;
import ch1.Constants;
import ch4.ChannelOperationExamples;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by binea
 * Date: 13/1/2018
 * TIME: 4:20 PM
 */
public class ClientTest {
    public static void main(String args[]) {
//        ConnectExample.connect(Constants.PORT);
//        ChannelOperationExamples.writingToChannel();
        testChannel();
    }

    private static void testChannel() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class).handler(
                new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(final NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                Channel channel = ctx.channel();
                                if (channel.isActive()) {
                                    ChannelOperationExamples.writingToChannel(channel);
                                }
                            }
                        });
                    }
                }).connect(new InetSocketAddress(Constants.HOST, Constants.PORT));
    }
}
