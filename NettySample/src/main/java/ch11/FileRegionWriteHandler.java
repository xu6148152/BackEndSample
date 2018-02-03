package ch11;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by binea
 * Date: 3/2/2018
 * TIME: 4:06 PM
 */
public class FileRegionWriteHandler extends ChannelInboundHandlerAdapter {
    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();
    private static final File FILE_FROM_SOMEWHERE = new File("");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        File file = FILE_FROM_SOMEWHERE;
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        FileInputStream in = new FileInputStream(file);
        FileRegion region = new DefaultFileRegion(in.getChannel(), 0, file.length());
        channel.writeAndFlush(region).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {

                }
            }
        });
    }
}
