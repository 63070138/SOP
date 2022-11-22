package SumServer;



import com.proto.sum.MyNum;
import com.proto.sum.SumRequest;
import com.proto.sum.SumResponse;
import com.proto.sum.SumServiceGrpc;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver){
        //Block 1:extract data required
        MyNum mynum = request.getNumbers();
        int num1 = mynum.getNum1();
        int num2 = mynum.getNum2();

        //Block 2:create the response message
        int result = num1+num2;
        System.out.println(String.format("Server Output: %d + %d = %d", num1,num2,result));
        SumResponse response = SumResponse.newBuilder()
                .setResult(result)
                .build();

        //Block 3: send the response
        responseObserver.onNext(response);

        //Block4: complete the RPC call
        responseObserver.onCompleted();
    }
}
