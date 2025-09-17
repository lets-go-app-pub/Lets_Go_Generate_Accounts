package site.lets_go_support.send_files_to_server.Clients;

/*
public class MathTestClient {

    private static int firstNum = 5;
    private static int secondNum = 12;

    public static void runClient() {
        Log.i("MathTestClient", "MathTestClient Running");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("23.254.204.109", 50051)
                .usePlaintext()
                .build();

        Log.i("MathTestClient", "Creating stub for numbers: " +  firstNum + " & " + secondNum);

        MathTestGrpc.MathTestBlockingStub mathTestClient = MathTestGrpc.newBlockingStub(channel);

        MathRequest mathRequest = MathRequest.newBuilder()
                .setA(firstNum)
                .setB(secondNum)
                .build();

        MathReply mathReply = mathTestClient.sendRequest(mathRequest);
        Log.i("MathTestClient", "Sum is: " + mathReply.getResult());

        Log.i("MathTestClient", "Shutting down channel");

        channel.shutdown();
    }
}*/
