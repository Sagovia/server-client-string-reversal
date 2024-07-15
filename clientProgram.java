// THIS IS ALL CODE FOR THE CLIENT

import java.net.UnknownHostException;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class myClient
{
    public static void main(String[] args)
    {
        System.out.println("This is the client. Enter a message to have it reversed.");
        Scanner userInput = new Scanner(System.in);

        boolean SENTINEL = true;
        try // Needed for IOException
        {
            // For the sake of testing I'll use localhost (loopback address) since
            // i only have 1 machine to test with, but realistically this should be
            // replaced with the other host you'll be communicating with (the server IP)
            Socket mySocket = new Socket("localhost", 6123);

            // Nest the streams in buffers so we can use the .readLine() methods
            BufferedReader myBufferedReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            BufferedWriter myBufferedWriter = new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()));


            while(SENTINEL)
            {
                String msg = userInput.nextLine(); // Get message from client to send

                myBufferedWriter.write(msg + "\n"); // new line to keep formatting consistent so .readLine() works
                myBufferedWriter.flush(); // make sure nothing will get stuck in buffer

                System.out.println(myBufferedReader.readLine());

                if(msg.equals("end"))
                {
                    SENTINEL = false;
                }
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}