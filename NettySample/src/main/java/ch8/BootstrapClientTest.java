package ch8;

/**
 * Created by binea
 * Date: 27/1/2018
 * TIME: 3:12 PM
 */
public class BootstrapClientTest {
    public static void main(String args[]) {
        testInvalidBootstrapClient();
    }

    private static void testBootstrapClientWithOptionsAttrs() {
        BootstrapClientWithOptionsAndAttrs bootstrapClientWithOptionsAndAttrs = new BootstrapClientWithOptionsAndAttrs();
        bootstrapClientWithOptionsAndAttrs.boostrap();
    }

    private static void testBootstrapClient() {
        BootstrapClient bootstrapClient = new BootstrapClient();
        bootstrapClient.bootstrap();
    }

    private static void testBootstrapDatagramChannel() {
        BootstrapDatagramChannel bootstrapDatagramChannel = new BootstrapDatagramChannel();
        bootstrapDatagramChannel.bootstrap();
    }

    private static void testBootstrapServer() {
        BootstrapServer bootstrapServer = new BootstrapServer();
        bootstrapServer.bootstrap();
    }

    private static void testBootstrapSharingEventLoopGroup() {
        BootstrapSharingEventLoopGroup bootstrapSharingEventLoopGroup = new BootstrapSharingEventLoopGroup();
        bootstrapSharingEventLoopGroup.bootstrap();
    }

    private static void testBootstrapWithInitializer() {
        BootstrapWithInitializer bootstrapWithInitializer = new BootstrapWithInitializer();
        try {
            bootstrapWithInitializer.bootstrap();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testGracefulShutDown() {
        GracefulShutdown gracefulShutdown = new GracefulShutdown();
        gracefulShutdown.bootstrap();
    }

    private static void testInvalidBootstrapClient() {
        InvalidBootstrapClient invalidBootstrapClient = new InvalidBootstrapClient();
        invalidBootstrapClient.bootstrap();
    }
}
