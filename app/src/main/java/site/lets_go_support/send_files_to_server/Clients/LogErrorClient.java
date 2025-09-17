package site.lets_go_support.send_files_to_server.Clients;

/*
public class LogErrorClient {

    private static AtomicInteger timesRun = new AtomicInteger(0);
    private static ManagedChannel channel = ManagedChannelBuilder.forAddress("10.0.2.2", 50051)
            .usePlaintext()
            .build();

    public boolean logError() {

        SendErrorRequest request = SendErrorRequest.newBuilder()
                .setErrorMessage("ERROR: " + timesRun.incrementAndGet())
                .setCollectionName("COLLECTION")
                .setLetsGoVersion(1.234)
                .build();

        SendErrorServiceGrpc.SendErrorServiceBlockingStub client = SendErrorServiceGrpc.newBlockingStub(channel);
        boolean exceptionReturn = false;
        SendErrorResponse response = client.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).sendErrorRPC(request);
        try {

            Log.i("LogErrorClient","Success: " + timesRun.incrementAndGet() + " Response: " + response.getReturnStatus().toString());
        }
        catch (StatusRuntimeException e){
            if(e.getMessage() != null) {
                Log.i("LogErrorClient", "Exception Msg:" + e.getLocalizedMessage());

                String message = e.getMessage();

                //this is inability to connect for some reason, should retry
                if(message.equals("UNAVAILABLE")){

                }
                //this means the deadline went over, should retry
                else if(message.length() > 17 && (message.substring(0,17)).equals("DEADLINE_EXCEEDED")){

                }
            }
        }

        return true;
*//*

        SendErrorServiceGrpc.SendErrorServiceFutureStub client = SendErrorServiceGrpc.newFutureStub(channel);

        ListenableFuture<SendErrorResponse> future = client.withDeadlineAfter(5000, TimeUnit.MILLISECONDS).sendErrorRPC(request);

        Futures.addCallback(future, new FutureCallback<SendErrorResponse>() {
            @Override
            public void onSuccess(SendErrorResponse result) {

                Log.i("LogErrorClient","Success$: " + timesRun.incrementAndGet());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("LogErrorClient","Failure: " + t.getLocalizedMessage());
            }
        },MoreExecutors.directExecutor());

        Log.i("LogErrorClient","addedCallBack");

        SendErrorResponse response = null;
*//*
        //this will allow me to get the response and return it but it will block the thread until it gets a response
        //so it essentiall turns it into a blocking stub
        //response = future.get();

    }
}*/

/*

message SendErrorRequest {
  string error_message = 1;
  string collection_name = 2;
  double lets_go_version = 3;
}

message SendErrorResponse {
  enum Status {
    CONNECTION_ERROR = 0;
    SUCCESSFUL = 1;
    OUTDATED_VERSION = 2;
    FAIL = 3;
  }
  Status return_status = 1;
}

service SendErrorService {
  rpc SendErrorRPC(SendErrorRequest) returns (SendErrorResponse) {};
}

 */