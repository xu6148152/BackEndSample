package ch6;

import io.netty.channel.*;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 3:56 PM
 */
public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        promise.addListener((ChannelFutureListener) future -> {
            if (!future.isSuccess()) {
                future.cause().printStackTrace();
                future.channel().close();
            }
        });
    }
}
