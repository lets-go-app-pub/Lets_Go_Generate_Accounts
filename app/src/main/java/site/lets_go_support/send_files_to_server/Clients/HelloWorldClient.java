package site.lets_go_support.send_files_to_server.Clients;

/*
public class HelloWorldClient {

    public void doUnaryCall(){

        ManagedChannel channel = ManagedChannelBuilder.forAddress("10.0.2.2", 50051)
                .usePlaintext()
                .build();

        Log.i("HelloWorldClient", "Running Unary Call HelloWorldClient");

        String firstName = "Jeremiah";

        HelloRequest request = HelloRequest.newBuilder()
                .setName(firstName)
                .build();

        Log.i("HelloWorldClient", "Creating stub");

        GreeterGrpc.GreeterBlockingStub greetClient = GreeterGrpc.newBlockingStub(channel);

        Log.i("HelloWorldClient", "Sending request with FirstName: " + firstName);
        System.out.println();

        HelloReply reply = greetClient.sayHello(request);

        Log.i("HelloWorldClient", "Received response " + reply.getMessage());

        Log.i("HelloWorldClient", "Shutting down channel");
        channel.shutdown();

    }

}*/
