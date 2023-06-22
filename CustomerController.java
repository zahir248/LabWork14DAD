// Part 3 Exercise 3

package server.controller;

import java.util.ArrayList;
import java.util.List;
import model.Customer;

public class CustomerController {
    private List<Customer> customers;

    /**
     * Constructs a new CustomerController and initializes the list with sample customer data.
     */
    public CustomerController() {
        customers = new ArrayList<>();
        createSampleCustomerData();
    }

    /**
     * Creates a list of sample customer data.
     * This is a private method.
     */
    private void createSampleCustomerData() {
        // Add 10 sample customers to the list
        for (int i = 1; i <= 10; i++) {
            Customer customer = new Customer();
            customer.setCustomerId(i);
            customer.setName("Customer " + i);
            customers.add(customer);
        }
    }

    /**
     * Search customer by name.
     * @param name The customer name to search for.
     * @return The Customer object if found, null otherwise.
     * This is a public method.
     */
    public Customer searchCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    /**
     * Search customer by ID.
     * @param id The customer ID to search for.
     * @return The Customer object if found, null otherwise.
     * This is a public method.
     */
    public Customer searchCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null; // Customer not found
    }

    /**
     * Get the list of customers.
     * @return The list of customers.
     * This is a public method.
     */
    public List<Customer> getCustomers() {
        return customers;
    }
}
