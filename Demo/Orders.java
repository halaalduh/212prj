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
    private int orderId;
    private int customerRefrence;
    private Integer[] products;
    private double total_price;
    private LocalDate date;
    private String status; // pending, shipped, delivered, cancelled

    // date string must be "dd/MM/yyyy"
    public Orders(int oid, int cid, Integer[] pids, double total, String dateStr, String status) {
        this.orderId = oid;
        this.customerRefrence = cid;
        this.products = pids;
        this.total_price = total;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = LocalDate.parse(dateStr, fmt);
        this.status = status;
    }

    // getters used by managers
    public int getOrderId() { return orderId; }
    public int getCustomerRefrence() { return customerRefrence; }
    public Integer[] getProducts() { return products; }
    public double getTotal_price() { return total_price; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }

    // setters used by OrderManager
    public void setStatus(String s) { this.status = s; }

    @Override public String toString() {
        return "Order(" + orderId + ", customer=" + customerRefrence +
               ", total=" + total_price + ", status=" + status + ", date=" + date + ")";
    }
}