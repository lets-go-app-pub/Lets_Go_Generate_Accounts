package site.lets_go_support.send_files_to_server.Clients;

import android.util.Log;

import com.google.protobuf.ByteString;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import send_picture_for_testing.SendPictureForTesting;
import send_picture_for_testing.SendPicturesForTestingServiceGrpc;

public class SendPicturesForTesting {

    public byte[] runClient(byte[] passedByteArray, byte[] thumbnailByteArray, ManagedChannel channel){

        Log.i("TestByteClient", "Creating Stub");

        SendPicturesForTestingServiceGrpc.SendPicturesForTestingServiceBlockingStub client = SendPicturesForTestingServiceGrpc.newBlockingStub(channel);

//        String hello = "hello";
//        byte[] byteArray = hello.getBytes();

        SendPictureForTesting.SendPicturesForTestingRequest request = SendPictureForTesting.SendPicturesForTestingRequest.newBuilder()
                .setSendBytes(ByteString.copyFrom(passedByteArray))
                .setSendThumbnail(ByteString.copyFrom(thumbnailByteArray))
                .build();

        SendPictureForTesting.SendPicturesForTestingResponse response = null;
        try {
            response = client.withDeadlineAfter(30, TimeUnit.SECONDS).sendPictureForTestingRPC(request);
        }
        catch(Exception e){
            Log.i("TestByteClient", "Error: " + e.getMessage());
        }

        byte[] sss = new byte[0];
        //return response.getReceiveBytes().toByteArray();
        return sss;
    }
}
