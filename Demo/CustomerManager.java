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

    private LinkedList<Customers> customers = new LinkedList<>();
    private Scanner in = new Scanner(System.in);

    // Constructor: load customers from CSV
    public CustomerManager(String fileName) {
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            reader.nextLine(); // skip header
            
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();

                Customers c = new Customers(id, name, email);

                if (customers.empty()) customers.insert(c);
                else {
                    customers.findFirst();
                    while (!customers.last())
                        customers.findNext();
                    customers.insert(c);
                }
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("Error loading customers: " + ex.getMessage());
        }
    }

    public LinkedList<Customers> getCustomers() {
        return customers;
    }

    // Search customer by ID
    public Customers searchCustomerById(int id) {
        if (customers.empty()) return null;

        customers.findFirst();
        while (true) {
            if (customers.retrieve().getCustomerId() == id)
                return customers.retrieve();
            if (customers.last()) break;
            customers.findNext();
        }
        return null;
    }

    // Check if ID is in use
    public boolean checkCustomerId(int id) {
        return searchCustomerById(id) != null;
    }

    // Register new customer
    public void registerCustomer() {

        System.out.print("Enter Customer ID: ");
        int id = in.nextInt();
        while (checkCustomerId(id)) {
            System.out.println("ID already exists. Enter another: ");
            id = in.nextInt();
        }
        in.nextLine(); 

        System.out.print("Enter Name: ");
        String name = in.nextLine();

        System.out.print("Enter Email: ");
        String email = in.nextLine();

        Customers cust = new Customers(id, name, email);

        if (customers.empty()) customers.insert(cust);
        else {
            customers.findFirst();
            while (!customers.last())
                customers.findNext();
            customers.insert(cust);
        }

        System.out.println("Customer registered successfully.");
    }

    // Print customer's order history
    public void printOrderHistory(int id) {
        Customers cust = searchCustomerById(id);

        if (cust == null) {
            System.out.println("✘ No such customer.");
            return;
        }

        if (cust.getOrders().empty()) {
            System.out.println("No orders for " + cust.getName());
            return;
        }

        System.out.println("\nOrder history for " + cust.getName() + ":");
        LinkedList<Integer> list = cust.getOrders();
        list.findFirst();
        while (true) {
            System.out.println("Order ID: " + list.retrieve());
            if (list.last()) break;
            list.findNext();
        }
    }
}