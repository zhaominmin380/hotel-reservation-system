package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    //create map to store customer (email)
    private final Map<String, Customer> customers = new HashMap<>();

    //singleton
    private final static CustomerService reference = new CustomerService();
    private CustomerService() {}
    public static CustomerService getInstance() {
        return reference;
    }

    //method
    public void addCustomer(String email, String firstName, String lastName) {
        if(customers.containsKey(email)) {
            throw new IllegalArgumentException("Customer with email " + email + " already exists");
        }

        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    public Customer getCustomer(String email) {
        Customer customer = customers.get(email);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with email " + email + " does not exist.");
        }
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }
}
