// THIS IS ALL CODE FOR THE SERVER
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class myServer
{
    public static void main(String[] args)
    {
        boolean SENTINEL = true;

        try
        {
            // We'll be listening for a request on port 6123
            ServerSocket myServerSocket = new ServerSocket(6123);

            // Now wait for a client to contact the server
            // .accept() returns a Socket object from the connection
            Socket mySocket = myServerSocket.accept();

            // Now create BufferedReaders and BufferedWriters to we can read and write to the stream
            BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            BufferedWriter myBufferedWriter = new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()));

            while(SENTINEL)
            {
                // Will pause program until data is available to be read from the buffered reader
                String clientMsg = myBufferedReader.readLine();

                String reversedMsg = reverse(clientMsg);

                myBufferedWriter.write(reversedMsg + "\n"); // Needed since the way that buffered writer works is to use a new line char as a delimiter
                // Make sure it writes even if buffer is not full
                myBufferedWriter.flush();

                if(clientMsg.equals("end"))
                {
                    SENTINEL = false;
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static String reverse(String input)
    {
        if(input.isEmpty())
        {
            return("");
        }
        else
        {
            return(input.substring(input.length() - 1) + reverse(input.substring(0, input.length() - 1)));
        }
    }
}