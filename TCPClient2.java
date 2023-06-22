// Part 3 Exercise 7

package client.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient2 {
private static final int PORT = 1234;
private static final String SERVER_IP = "localhost";
	
	/**
	 * Starts the TCP client-side application that sends customer IDs to the server,
	 * receives a customer object from the server, and displays the details of the object.
	 * The application demonstrates sending existing and non-existing customer IDs.
	 *
	 * @param args The command line arguments (not used).
	 */
	public static void main(String[] args) {
	    try (Socket clientSocket = new Socket(SERVER_IP, PORT)) {
	        System.out.println("Connected to server: " + SERVER_IP);

	        // Create input and output streams for the client socket
	        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

	        // Send customer IDs to the server
	        sendCustomerId(outToServer, 123); // Existing customer ID
	        sendCustomerId(outToServer, 456); // Existing customer ID
	        sendCustomerId(outToServer, 789); // Non-existing customer ID

	        // Receive and display the customer object from the server
	        receiveAndDisplayCustomerObject(inFromServer); // Existing customer ID
	        receiveAndDisplayCustomerObject(inFromServer); // Existing customer ID
	        receiveAndDisplayCustomerObject(inFromServer); // Non-existing customer ID

	        // Close the client socket
	        clientSocket.close();
	        System.out.println("Client connection closed.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private static void sendCustomerId(PrintWriter outToServer, int customerId) {
	    outToServer.println(customerId);
	    System.out.println("Sent customer ID to server: " + customerId);
	}

	private static void receiveAndDisplayCustomerObject(BufferedReader inFromServer) throws IOException {
	    String response = inFromServer.readLine();
	    System.out.println("Received customer object from server: " + response);
	}


}
