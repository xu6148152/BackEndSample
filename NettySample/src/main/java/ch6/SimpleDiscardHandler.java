package ch6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 3:59 PM
 */
@ChannelHandler.Sharable
public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
