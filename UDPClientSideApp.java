// Part 2 Exercise 2

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientSideApp {

    public static void main(String[] args) {
        // Server address and port
        String serverAddress = "localhost";
        int serverPort = 8081;

        try (DatagramSocket socket = new DatagramSocket()) {
            // Prompt user to enter a sentence
            String sentence = "Hello, how are you?";

            // Convert sentence to bytes
            byte[] sentenceBytes = sentence.getBytes();

            // Send sentence to server
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            DatagramPacket requestPacket = new DatagramPacket(sentenceBytes, sentenceBytes.length,
                    serverInetAddress, serverPort);
            socket.send(requestPacket);

            System.out.println("UDPClient: Sentence sent to server");

            // Receive response from server
            byte[] buffer = new byte[65535];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            // Process response data
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());

            System.out.println("UDPClient: Response from server:");
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
