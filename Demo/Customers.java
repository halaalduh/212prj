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
    private int customerId;
    private String name;
    private String email;
    private LinkedList<Integer> orders = new LinkedList<>();

    public Customers(int id, String name, String email) {
        this.customerId = id;
        this.name = name;
        this.email = email;
    }

    // getters used by managers
    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LinkedList<Integer> getOrders() { return orders; }

    // optional setters
    public void setName(String n) { this.name = n; }
    public void setEmail(String e) { this.email = e; }

    // order list helpers (used by CustomerManager / OrderManager)
    public void addOrder(int oid) {
        if (orders.empty()) orders.insert(oid);
        else { orders.findFirst(); while(!orders.last()) orders.findNext(); orders.insert(oid); }
    }

    public void removeOrder(int oid) {
        if (orders.empty()) return;
        orders.findFirst();
        while (true) {
            if (orders.retrieve() == oid) { orders.remove(); return; }
            if (orders.last()) break;
            orders.findNext();
        }
    }

    @Override public String toString() {
        return "Customer(" + customerId + ", " + name + ", " + email + ")";
    }
}