package ch11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * Created by binea
 * Date: 3/2/2018
 * TIME: 4:29 PM
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SSLContext context;

    private final boolean startTls;

    public SslChannelInitializer(SSLContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        SSLEngine sslEngine = context.createSSLEngine();
        ch.pipeline().addFirst("ssl", new SslHandler(sslEngine, startTls));
    }
}
