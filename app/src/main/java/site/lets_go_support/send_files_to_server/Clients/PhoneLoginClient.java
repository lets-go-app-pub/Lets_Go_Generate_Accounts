package site.lets_go_support.send_files_to_server.Clients;

import android.util.Log;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/*
public class PhoneLoginClient {

    private static ManagedChannel myChannel = ManagedChannelBuilder.forAddress("10.0.2.2", 50051)
            .usePlaintext()
            .build();

    public static void runClient(String device_id) {

        Log.i("PhoneLoginClient", "Running Unary Call PhoneLoginClient");

        String firstName = "Jeremiah";

        PhoneLoginRequest request = PhoneLoginRequest.newBuilder()
                .setPhoneNumber("+17023033064")
                .setDeviceId("Three")
                .setLetsGoVersion(5)
                .build();

        Log.i("PhoneLoginClient", "Creating stub");

        PhoneLoginServiceGrpc.PhoneLoginServiceBlockingStub phoneLoginClient = PhoneLoginServiceGrpc.newBlockingStub(myChannel);

        Log.i("PhoneLoginClient", "Sending request with FirstName: " + firstName);
        System.out.println();

        PhoneLoginResponse response = phoneLoginClient.withWaitForReady().phoneLoginRPC(request);

        Log.i("PhoneLoginClient", "Received response Status: " + response.getStatus() + " Token: " + response.getLoginToken());

        //TODO: something happens when the server goes down while the connecting is active
        Log.i("PhoneLoginClient", "Shutting down channel");
    }
}*/
