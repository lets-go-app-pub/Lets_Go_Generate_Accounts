package site.lets_go_support.send_files_to_server.Clients;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
/*
public class SendNameClient {

    public static List<String> runClient(String firstName){
        Log.i("SendNameClient", "SendNameClient Running");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("hwsrv-708012.hostwindsdns.com", 50051)
                .usePlaintext()
                .build();

        Log.i("SendNameClient", "Creating Stub");

        SendNameServiceGrpc.SendNameServiceBlockingStub sendNameClient = SendNameServiceGrpc.newBlockingStub(channel);

        SendName.SendNameRequest sendNameRequest = SendName.SendNameRequest.newBuilder()
                .setFirstName(firstName)
                .build();

        SendName.SendNameResponse sendNameResponse;

        try {
            sendNameResponse = sendNameClient
                    .withWaitForReady()
                    .withDeadlineAfter(3000, TimeUnit.MILLISECONDS)
                    .sendNameRPC(sendNameRequest);
        } catch (StatusRuntimeException e) {
            sendNameResponse = SendName.SendNameResponse.newBuilder()
                    .setFirst("hello")
                    .build();
        }

        List<String> returnString = Arrays.asList(sendNameResponse.getFirst(), sendNameResponse.getLast(), sendNameResponse.getPhone());

        Log.i("SendNameClient", "Shutting down channel");

        channel.shutdown();

        return returnString;

    }

}*/
