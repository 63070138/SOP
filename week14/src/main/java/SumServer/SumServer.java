package SumServer;


import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SumServer {
    public static void main(String[] args){
        System.out.println("Hello Sum gRPC ");
        Server server = ServerBuilder.forPort(50054)
                .addService(new SumServiceImpl())
                .build();
        try {
            server.start();
            System.out.println("Sum Server Start");
        }catch (IOException e){
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(
                ()->{
                    System.out.println("Received Shutdown Request");
                    server.shutdown();
                    System.out.println("Successfully Shutdown Server");
                }
        ));
        try {
            server.awaitTermination();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
