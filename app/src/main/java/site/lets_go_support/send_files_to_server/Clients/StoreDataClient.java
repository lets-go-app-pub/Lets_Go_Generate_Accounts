package site.lets_go_support.send_files_to_server.Clients;

/*
public class StoreDataClient {

    public static String runClient(List<String> args){
        Log.i("StoreDataClient", "StoreDataClient Running");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("23.254.204.109", 50051)
                .usePlaintext()
                .build();

        Log.i("StoreDataClient", "Creating Stub");

        StoreDataServiceGrpc.StoreDataServiceBlockingStub storeDataClient = StoreDataServiceGrpc.newBlockingStub(channel);

        StoreData.StoreDataRequest storeDataRequest = StoreData.StoreDataRequest.newBuilder()
                .setFirstName(args.get(0))
                .setLastName(args.get(1))
                .setPhoneNumber(args.get(2))
                .build();

        StoreData.StoreDataResponse storeDataResponse = storeDataClient.storeDataRPC(storeDataRequest);

        Log.i("StoreDataClient", "Response is: " + storeDataResponse.getResponse());

        Log.i("StoreDataClient", "Shutting down channel");

        channel.shutdown();

        return storeDataResponse.getResponse();

    }
}*/
