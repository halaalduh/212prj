/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;

/**
 *
 * @author randd
 */

import java.io.File;
import java.util.Scanner;
public class CustomerManager {
     private DoubleLinkedList<Customers> customers = new DoubleLinkedList<>();

    public CustomerManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); // skip header
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                customers.insert(new Customers(Integer.parseInt(data[0]), data[1], data[2]));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DoubleLinkedList<Customers> getCustomers() { return customers; }

    public Customers searchCustomerById(int id) {
        if (customers.empty()) return null;
        customers.findFirst();
        while (!customers.last()) {
            if (customers.retrieve().getCustomerId() == id) return customers.retrieve();
            customers.findNext();
        }
        if (customers.retrieve().getCustomerId() == id) return customers.retrieve();
        return null;
    }

    public boolean checkCustomerId(int id) {
        return (searchCustomerById(id) != null);
    }

    // Print order history for a customer
    public void printOrderHistory(int id) {
        Customers c = searchCustomerById(id);
        if (c == null) {
            System.out.println("Customer not found");
            return;
        }
        if (c.getOrders().empty()) {
            System.out.println("No orders for " + c.getName());
            return;
        }
        System.out.println("Order history for " + c.getName() + ":");
        c.getOrders().findFirst();
        while (!c.getOrders().last()) {
            System.out.println(c.getOrders().retrieve());
            c.getOrders().findNext();
        }
        System.out.println(c.getOrders().retrieve());
    }

}






//halaaaaa


import java.io.File;
import java.util.Scanner;
public class CustomerManager {
    
     private DoubleLinkedList<Customers> customers = new DoubleLinkedList<>();
     
    //reads customer data from a CSV file and stores them in the list
    public CustomerManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); 
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                customers.insert(new Customers(Integer.parseInt(data[0]), data[1], data[2]));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("Error "+ ex.getMessage());
        }
    }
    
    
    // Returns the list of customers
    public DoubleLinkedList<Customers> getCustomers() {
        return customers; }
   
    
    
    // Searches for a customer by ID
    public Customers searchCustomerById(int id) {
    if (customers.empty()) {
        System.out.println("No customers available.");
        return null;
    }
    customers.findFirst();
    while (!customers.last()) {
        if (customers.retrieve().getCustomerId() == id) {
            System.out.println("Customer found: " + customers.retrieve().getName());
            return customers.retrieve();
        }
        customers.findNext();
    }
    if (customers.retrieve().getCustomerId() == id) {
        System.out.println("Customer found: " + customers.retrieve().getName());
        return customers.retrieve();
    }
    System.out.println("Customer with ID " + id + " not found.");
    return null;
}

    // Checks if a customer ID already exists
    public boolean checkCustomerId(int cid) {
        return (searchCustomerById(cid) != null);
    }
    
    // Registers a new customer from user input
    public void registerCustomer() {
     Scanner sc = new Scanner(System.in);

     System.out.print("Customer ID: ");
     int id = sc.nextInt();
     sc.nextLine(); 

     System.out.print("Name: ");
     String name = sc.nextLine();

     System.out.print("Email: ");
     String email = sc.nextLine();

     Customers cust = new Customers();     
     cust.customerId = id;                
     cust.email = email;

        if(customers.empty()) {
          customers.insert(cust);
        } else {
          customers.findLast();
          customers.insert(cust);
    }
    System.out.println("Customer registered.");
   }

    // Prints the order history for a specific customer
    public void printOrderHistory(int id) {
        Customers cust = searchCustomerById(id);
        if (cust == null) {
            System.out.println("Customer not found");
            return;
        }
        if (cust.getOrders().empty()) {
            System.out.println("No orders for " + cust.getName());
            return;
        }
        System.out.println("Order history for " + cust.getName() + ":");
        cust.getOrders().findFirst();
        while (!cust.getOrders().last()) {
            System.out.println(cust.getOrders().retrieve());
            cust.getOrders().findNext();
        }
        System.out.println(cust.getOrders().retrieve());
    }

}

