// Part 3 Exercise 9

package server.app;

import java.io.*;
import java.net.*;
import java.util.List;

import model.Customer;
import server.controller.CustomerController;

public class TCPServer3 {
    private static final int PORT = 1234;

    /**
     * Starts the TCP server-side application that returns a list of customers to the client.
     * The server logs all its operations and interactions with the client.
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

                // Retrieve the list of customers (assuming you have a CustomerController instance)
                CustomerController customerController = new CustomerController();
                List<Customer> customerList = customerController.getCustomers();

                // Send the list of customers to the client
                sendCustomerList(outToClient, customerList);

                // Close the client socket
                clientSocket.close();
                System.out.println("Client connection closed.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendCustomerList(PrintWriter outToClient, List<Customer> customerList) {
        for (Customer customer : customerList) {
            outToClient.println(customer.toString());
        }
        outToClient.println("END"); // Signal the end of the customer list
        System.out.println("Sent customer list to client.");
    }
}
