// Part 3 Exercise 10

package client.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Customer;

public class TCPClient3 {
    private static final int PORT = 1234;
    private static final String SERVER_IP = "localhost";

    /**
     * Starts the TCP client-side application that receives a list of customers from the server
     * and displays the details of the clients in alphabetical order.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(SERVER_IP, PORT)) {
            System.out.println("Connected to server: " + SERVER_IP);

            // Create input and output streams for the client socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Receive the list of customers from the server
            List<Customer> customerList = receiveCustomerList(inFromServer);

            // Sort the customer list alphabetically
            sortCustomerList(customerList);

            // Display the details of the customers
            displayCustomerList(customerList);

            // Close the client socket
            clientSocket.close();
            System.out.println("Client connection closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Customer> receiveCustomerList(BufferedReader inFromServer) throws IOException {
        List<Customer> customerList = new ArrayList<>();
        String response;
        while (!(response = inFromServer.readLine()).equals("END")) {
            // Parse the response and create a Customer object
            Customer customer = parseCustomerString(response);
            if (customer != null) {
                customerList.add(customer);
            }
        }
        return customerList;
    }

    private static Customer parseCustomerString(String customerString) {
        // Implement the logic to parse the customerString and create a Customer object
        // based on the structure and format of your Customer class.
        // Return the created Customer object.
        return null; // Placeholder, replace with the actual implementation.
    }

    private static void sortCustomerList(List<Customer> customerList) {
        Collections.sort(customerList, new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                // Implement the comparison logic based on the desired sorting criteria for Customer objects
                // Return a negative value if c1 should come before c2,
                // a positive value if c1 should come after c2,
                // or 0 if c1 and c2 are considered equal in terms of sorting.
                return c1.getName().compareTo(c2.getName()); // Example: Sort by customer name
            }
        });
    }

    private static void displayCustomerList(List<Customer> customerList) {
        System.out.println("Customer List:");
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
    }
}
