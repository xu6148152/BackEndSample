package ch6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 3:58 PM
 */
@ChannelHandler.Sharable
public class SharableHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel read message " + msg);
        ctx.fireChannelRead(msg);
    }
}
