// Part 3 Exercise 6

package server.app;

import java.io.*;
import java.net.*;

import model.Customer;
import server.controller.CustomerController;

public class TCPServer2 {
    private static final int PORT = 1234;

    /**
     * Starts the TCP server-side application that processes requests from a TCP client.
     * The server receives an integer representing a customer ID from the client,
     * searches for the customer based on the ID, and returns a customer object to the client.
     * The server also logs all its operations and interactions with the clients.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create input and output streams for the client socket
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read customer ID from the client
                int customerId = Integer.parseInt(inFromClient.readLine());
                System.out.println("Received customer ID from client: " + customerId);

                // Find the customer based on the ID (assuming you have a CustomerController instance)
                CustomerController customerController = new CustomerController();
                Customer customer = customerController.searchCustomerById(customerId);

                // Send customer object to the client
                if (customer != null) {
                    outToClient.println(customer.toString());
                } else {
                    outToClient.println("Customer not found");
                }

                // Close the client socket
                clientSocket.close();
                System.out.println("Client connection closed.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
