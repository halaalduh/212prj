/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author randd
 */
public class Orders {
      int orderId;
    int customerReference;  
    DoubleLinkedList<Products> products = new DoubleLinkedList<>();
    double total_price;
    LocalDate date;
    String status; // (pending, shipped, delivered, canceled)

    public Orders() {
        this.orderId = 0;
        this.customerReference = 0;
        this.total_price = 0;
        this.status = "pending";
        this.date = LocalDate.now();
    }

    public Orders(int orderId, int customerReference, double total_price, String date, String status) {
        this.orderId = orderId;
        this.customerReference = customerReference;
        this.total_price = total_price;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.date = LocalDate.parse(date); // yyyy-MM-dd format
        this.status = status;
    }

    // Getters
    public int getOrderId() { return orderId; }
    public int getCustomerReference() { return customerReference; }
    public DoubleLinkedList<Products> getProducts() { return products; }
    public double getTotal_price() { return total_price; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }

    // Setters
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setCustomerReference(int customerReference) { this.customerReference = customerReference; }
    public void setTotal_price(double total_price) { this.total_price = total_price; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }

    // Product handling
    public void addProduct(Products p) { products.insert(p); }

    public boolean removeProduct(Products p) {
        if (!products.empty()) {
            products.findFirst();
            while (!products.last()) {
                if (products.retrieve().equals(p)) {
                    products.remove();
                    return true;
                }
                products.findNext();
            }
            if (products.retrieve().equals(p)) {
                products.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "Order{" +
                "id=" + orderId +
                ", customerReference=" + customerReference +
                ", total=" + total_price +
                ", status='" + status + '\'' +
                ", date=" + date;
        if (!products.empty()) {
            str += ", products=[";
            products.findFirst();
            while (!products.last()) {
                str += products.retrieve().getName() + " ";
                products.findNext();
            }
            str += products.retrieve().getName() + "]";
        }
        str += "}";
        return str;
    }
}
