// Part 3 Exercise 2

package model;

public class Customer {
    private int customerId;
    private String name;

    /**
     * Get the customer ID.
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set the customer ID.
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Get the customer name.
     * @return The customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the customer name.
     * @param name The customer name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
