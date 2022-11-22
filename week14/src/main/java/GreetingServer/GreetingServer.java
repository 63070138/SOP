package GreetingServer;

import com.proto.dummy.DummyServiceGrpc;
import io.grpc.*;

import java.io.IOException;

public class GreetingServer {
    public static void main(String[] args){

        Server server = ServerBuilder.forPort(50055)
                .addService(new GreetServiceImpl())
                .build();


        try {
            server.start();
            System.out.println("Server Start");
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
