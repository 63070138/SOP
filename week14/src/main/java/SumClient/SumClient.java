package SumClient;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import com.proto.sum.MyNum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {
    public static void main(String[] args){
        System.out.println("Hello Sum gRPC Client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",50054)
                .usePlaintext()
                .build();
        System.out.println("Creating Sum Stub");

        SumServiceGrpc.SumServiceBlockingStub sumClient;
        sumClient = SumServiceGrpc.newBlockingStub(channel);

        MyNum numbers = MyNum.newBuilder()
                .setNum1(5)
                .setNum2(10)
                .build();

        SumRequest sumRequest = SumRequest.newBuilder()
                .setNumbers(numbers)
                .build();

        SumResponse sumResponse = sumClient.sum(sumRequest);

        System.out.println(sumResponse.getResult());


        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}
