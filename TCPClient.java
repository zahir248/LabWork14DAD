// Part 3 Exercise 5

package client.app;

import java.io.*;
import java.net.*;

public class TCPClient {
    private static final int PORT = 1234;
    private static final String SERVER_IP = "localhost";

    /**
     * Starts the TCP client-side application that sends customer names to the server,
     * receives a customer object from the server, and displays the details of the object.
     * The application demonstrates sending full names, partial names, and non-existing customers.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(SERVER_IP, PORT)) {
            System.out.println("Connected to server: " + SERVER_IP);

            // Create input and output streams for the client socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Send customer names to the server
            sendCustomerName(outToServer, "John Doe"); // Full name
            sendCustomerName(outToServer, "Mary"); // Partial name
            sendCustomerName(outToServer, "Non-existing Customer"); // Non-existing customer

            // Receive and display the customer object from the server
            receiveAndDisplayCustomerObject(inFromServer); // Full name
            receiveAndDisplayCustomerObject(inFromServer); // Partial name
            receiveAndDisplayCustomerObject(inFromServer); // Non-existing customer

            // Close the client socket
            clientSocket.close();
            System.out.println("Client connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendCustomerName(PrintWriter outToServer, String customerName) {
        outToServer.println(customerName);
        System.out.println("Sent customer name to server: " + customerName);
    }

    private static void receiveAndDisplayCustomerObject(BufferedReader inFromServer) throws IOException {
        String response = inFromServer.readLine();
        System.out.println("Received customer object from server: " + response);
    }
}
