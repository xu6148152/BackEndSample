package ch1;

import java.io.IOException;

/**
 * Created by binea
 * Date: 13/1/2018
 * TIME: 4:17 PM
 */
public class ServerTest {
    public static void main(String args[]) {
        BlockingIoExample blockingIoExample = new BlockingIoExample();
        try {
            System.out.println(String.format("start listener on %s port", Constants.PORT));
            blockingIoExample.serve(Constants.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
