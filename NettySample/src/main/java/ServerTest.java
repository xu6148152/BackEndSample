
import ch1.BlockingIoExample;
import ch1.Constants;
import ch4.NettyNioServer;
import ch4.PlainOioServer;

import java.io.IOException;

/**
 * Created by binea
 * Date: 13/1/2018
 * TIME: 4:17 PM
 */
public class ServerTest {
    public static void main(String args[]) {
//        testBlockingExample();
        testChannel();
    }

    private static void testBlockingExample() {
        BlockingIoExample blockingIoExample = new BlockingIoExample();
        try {
            System.out.println(String.format("start listener on %s port", Constants.PORT));
            blockingIoExample.serve(Constants.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testChannel() {
        NettyNioServer plainOioServer = new NettyNioServer();
        try {
            plainOioServer.serve(Constants.PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
