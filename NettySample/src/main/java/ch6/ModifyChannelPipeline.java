package ch6;

import ch6.channel.DummyChannelPipline;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelPipeline;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 3:43 PM
 */
public class ModifyChannelPipeline {
    private static final ChannelPipeline CHANNEL_PIPELINE_FROM_WOMEWHERE = DummyChannelPipline.DUMMY_INSTANCE;

    public static void modifyPipeline() {
        ChannelPipeline pipeline = CHANNEL_PIPELINE_FROM_WOMEWHERE;
        FirstHandler firstHandler = new FirstHandler();
        pipeline.addLast("handler1", firstHandler);
        pipeline.addFirst("handler2", new SecondHandler());
        pipeline.addLast("handler3", new ThirdHandler());

        pipeline.remove("handler3");
        pipeline.remove(firstHandler);
        pipeline.replace("handler2", "handler4", new FourthHandler());
    }

    private static final class FirstHandler extends ChannelHandlerAdapter {

    }

    private static final class SecondHandler extends ChannelHandlerAdapter {

    }

    private static final class ThirdHandler extends ChannelHandlerAdapter {

    }

    private static final class FourthHandler extends ChannelHandlerAdapter {

    }
}
