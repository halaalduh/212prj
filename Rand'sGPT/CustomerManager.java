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
