package site.lets_go_support.send_files_to_server.Clients;

/*public class SMSVerificationClient {

    public static void runClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("10.0.2.2", 50051)
                .usePlaintext()
                .build();

        Log.i("PhoneLoginClient", "Running Unary Call PhoneLoginClient");

        String firstName = "Jeremiah";

        SMSVerificationRequest request = SMSVerificationRequest.newBuilder()
                .setPhoneNumber("+17023033064")
                .setVerificationCode("111111")
                .setLetsGoVersion(1)
                .build();

        Log.i("PhoneLoginClient", "Creating stub");

        SMSVerificationServiceGrpc.SMSVerificationServiceBlockingStub smsVerificationClient = SMSVerificationServiceGrpc.newBlockingStub(channel);

        Log.i("PhoneLoginClient", "Sending request with FirstName: " + firstName);
        System.out.println();

        SMSVerificationResponse response = smsVerificationClient.sMSVerificationRPC(request);

        Log.i("PhoneLoginClient", "Received response Status: " + response.getStatus());

        //TODO: something happens when the server goes down while the connecting is active
        Log.i("PhoneLoginClient", "Shutting down channel");
        channel.shutdown();
    }

}*/
