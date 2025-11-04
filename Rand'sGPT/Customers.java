/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;

/**
 *
 * @author randd
 */
public class Customers {
     int customerId;
    String name;
    String email;
    DoubleLinkedList<Orders> orders = new DoubleLinkedList<>();

    public Customers() {
        this.customerId = 0;
        this.name = "";
        this.email = "";
    }

    public Customers(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Orders
    public DoubleLinkedList<Orders> getOrders() { return orders; }
    public void addOrder(Orders o) { orders.insert(o); }

    public boolean removeOrder(Orders o) {
        if (!orders.empty()) {
            orders.findFirst();
            while (!orders.last()) {
                if (orders.retrieve().equals(o)) {
                    orders.remove();
                    return true;
                }
                orders.findNext();
            }
            if (orders.retrieve().equals(o)) {
                orders.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "Customer{" +
                "id=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'';
        if (!orders.empty()) {
            str += ", orders=[";
            orders.findFirst();
            while (!orders.last()) {
                str += orders.retrieve() + " ";
                orders.findNext();
            }
            str += orders.retrieve() + "]";
        }
        str += "}";
        return str;
    }
}