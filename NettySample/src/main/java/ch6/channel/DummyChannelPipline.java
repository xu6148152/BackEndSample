package ch6.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelPipeline;

/**
 * Created by binea
 * Date: 21/1/2018
 * TIME: 3:46 PM
 */
public class DummyChannelPipline extends DefaultChannelPipeline {
    public static final ChannelPipeline DUMMY_INSTANCE = new DummyChannelPipline(null);

    protected DummyChannelPipline(Channel channel) {
        super(channel);
    }
}
