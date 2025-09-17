package site.lets_go_support.send_files_to_server.Clients;

/*
public class FindMatchesClient {

    public static void runClient() throws InterruptedException {

        Log.i("FindMatchesClient", "FindMatchesClient Running");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("10.0.2.2", 50051)
                .usePlaintext()
                .build();

        Log.i("FindMatchesClient", "Creating Stub");

        try {

            FindMatchesServiceGrpc.FindMatchesServiceBlockingStub client = FindMatchesServiceGrpc.newBlockingStub(channel);

            FindMatches.FindMatchesRequest request = FindMatches.FindMatchesRequest.newBuilder()
                    .setClientLatitude(122.0)
                    .setClientLongitude(96.3)
                    .setLetsGoVersion(1.0)
                    .setLoggedInToken("asdvbfedsh123456fcdauejd")
                    .setNumberMessages(3)
                    .build();

            Iterator<FindMatches.SingleMatchMessage> response = client.findMatchRPC(request);


            //NOTE: this will work properly and receive the messages one at a time
            while (response.hasNext()) {
                FindMatches.SingleMatchMessage nextMessage = response.next();
                Log.i("FindMatchesClient", "Before Time");
                TimeUnit.SECONDS.sleep(1);
                Log.i("FindMatchesClient", "After Time");
            }

        }
        catch (Exception e) {
            Log.i("FindMatchesClient", "Exception: " + e.getLocalizedMessage());
        }
    }

}*/
