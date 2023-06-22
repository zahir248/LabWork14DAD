// Part 3 Exercise 4

package server.app;

import java.io.*;
import java.net.*;

import model.Customer;
import server.controller.CustomerController;

public class TCPServer {
    private static final int PORT = 1234;

    /**
     * Starts the TCP server-side application that processes requests from a TCP client.
     * The server receives a string of data representing a customer from the client,
     * finds the customer based on the name, and returns a customer object to the client.
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

                // Read customer name from the client
                String customerName = inFromClient.readLine();
                System.out.println("Received customer name from client: " + customerName);

                // Find the customer based on the name (assuming you have a CustomerController instance)
                CustomerController customerController = new CustomerController();
                Customer customer = customerController.searchCustomerByName(customerName);

                // Send customer object to the client
                outToClient.println(customer != null ? customer.toString() : "Customer not found");

                // Close the client socket
                clientSocket.close();
                System.out.println("Client connection closed.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
