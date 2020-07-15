package lab.jlhgxu520.equipment.server;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcChannel {
//    public static final String SERVER_IP = "192.168.43.214";
    public static final String SERVER_IP = "192.168.102.56";
//    public static final String SERVER_IP = "192.168.3.16";
    private static final int PORT = 8282;
    public static ManagedChannel getChannel(){
        return ManagedChannelBuilder.forAddress(SERVER_IP,PORT)
                .usePlaintext().build();
    }
}
