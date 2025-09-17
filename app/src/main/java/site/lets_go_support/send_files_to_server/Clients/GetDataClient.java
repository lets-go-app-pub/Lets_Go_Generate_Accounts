package site.lets_go_support.send_files_to_server.Clients;

/*
public class GetDataClient {

    public static void runClient() {

        Log.i("GetDataClient", "GetDataClient Running");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("23.254.204.109", 50051)
                .usePlaintext()
                .build();

        Log.i("GetDataClient", "Creating Stub");

        GetDataServiceGrpc.GetDataServiceBlockingStub getDataClient = GetDataServiceGrpc.newBlockingStub(channel);

        GetData.GetDataRequest getDataRequest = GetData.GetDataRequest.newBuilder()
                .setFirstName("Jeremiah")
                .build();
        GetData.GetDataResponse getDataResponse = null;
        try {
            getDataResponse = getDataClient.getDataRPC(getDataRequest);
        }
        catch (StatusRuntimeException e){
            Log.i("GetDataClient", "Exception: " + e);
        }

        if (getDataResponse != null) {
            Log.i("GetDataClient", "Response is: " + getDataResponse.getResponse());
        }

        Log.i("GetDataClient", "Shutting down channel");

        channel.shutdown();

    }

}*/
