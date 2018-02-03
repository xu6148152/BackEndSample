package ch10;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Created by binea
 * Date: 3/2/2018
 * TIME: 3:14 PM
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
