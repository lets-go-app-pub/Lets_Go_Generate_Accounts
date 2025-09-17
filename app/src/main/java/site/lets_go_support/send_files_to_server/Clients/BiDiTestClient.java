package site.lets_go_support.send_files_to_server.Clients;

/*
public class BiDiTestClient {

    public static StreamObserver<BiDiStreamTest.BiDiRequest> requestObserver = null;

    public static String startBiDiTest(
            ManagedChannel channel,
            StreamObserver<BiDiStreamTest.BiDiResponse> responseObserver
    ) {

//        BiDiServiceGrpc.BiDiServiceStub asyncClient = BiDiServiceGrpc.newStub(channel);
//
//        if(requestObserver != null) {
//            requestObserver.onCompleted();
//            requestObserver = null;
//        }
//
//        requestObserver = asyncClient.withWaitForReady().
//                withDeadlineAfter(10, TimeUnit.MINUTES).biDiRPC(responseObserver);

        ChatMessageStream.ChatToServerRequest request = ChatMessageStream.ChatToServerRequest.newBuilder()
                .setLoggedInToken("heyYo")
                .build();

        StreamChatServiceGrpc.StreamChatServiceBlockingStub client = StreamChatServiceGrpc.newBlockingStub(channel);

        Log.i("chatStream", "Created client");

        String returnString = "";

        try {
            Iterator<ChatMessageStream.StreamChatToClient> responseIterator = client.withWaitForReady().withDeadlineAfter(
                    10, TimeUnit.MINUTES).streamChatRPC(request);

            Log.i("chatStream", "Sent To Server");

            //NOTE: this iterator seems to just wait until the onNext is called, also this entire thing runs sequentially
            // so until all of these

            boolean hasRun = false;
            while (responseIterator.hasNext()) {
                if(!hasRun) {
                    hasRun = true;
                    Log.i("chatStream", "first run");
                }

                ChatMessageStream.StreamChatToClient nextResponse = responseIterator.next();

                Log.i("chatStream", "OID: " + nextResponse.getMessageOid());
            }

            returnString = "Completed";
        }
        catch (Exception e) {
            Log.i("chatStream", "exception: " + Objects.requireNonNull(e.getLocalizedMessage()));
            if(e.getMessage() != null) {
                returnString = e.getMessage();
            }
        }

        Log.i("chatStream", "Finished; Return String: " + returnString);

        return returnString;
    }

}*/
