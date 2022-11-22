package GreetingServer;


import com.proto.greet.*;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver){
        //Block 1:extract data required
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        String lastName = greeting.getLastName();

        //Block 2:create the response message
        String result = "Hello "+firstName+" "+lastName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        //Block 3: send the response
        responseObserver.onNext(response);

        //Block4: complete the RPC call
        responseObserver.onCompleted();
    }
}
