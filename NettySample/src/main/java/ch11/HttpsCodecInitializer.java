package ch11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Created by binea
 * Date: 3/2/2018
 * TIME: 4:15 PM
 */
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {

    private final SSLContext context;

    private final boolean isClient;

    public HttpsCodecInitializer(SSLContext context, boolean isClient) {
        this.context = context;
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        SSLEngine sslEngine = context.createSSLEngine();
        pipeline.addLast("ssl", new SslHandler(sslEngine));

        if (isClient) {
            pipeline.addLast("codec", new HttpClientCodec());
        } else {
            pipeline.addLast("codec", new HttpServerCodec());
        }
    }
}
