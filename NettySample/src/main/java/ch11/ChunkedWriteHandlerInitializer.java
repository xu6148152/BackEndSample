package ch11;

import io.netty.channel.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedStream;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by binea
 * Date: 3/2/2018
 * TIME: 3:57 PM
 */
public class ChunkedWriteHandlerInitializer extends ChannelInitializer<Channel> {

    private final File file;
    private final SSLContext sslCtx;

    public ChunkedWriteHandlerInitializer(File file, SSLContext sslCtx) {
        this.file = file;
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new SslHandler(sslCtx.createSSLEngine()));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new WriteStreamHandler());
    }

    public final class WriteStreamHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            ctx.writeAndFlush(ctx);
            new ChunkedStream(new FileInputStream(file));
        }
    }
}
