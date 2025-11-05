/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;

/**
 *
 * @author randd
 */
public class Products {
    private int productId;
    private String name;
    private double price;
    private int stock;

    public Products(int id, String name, double price, int stock) {
        this.productId = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // getters used by managers
    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // setters used by ProductManager
    public void setName(String n) { this.name = n; }
    public void setPrice(double p) { this.price = p; }
    public void setStock(int s) { this.stock = s; }

    @Override public String toString() {
        return "Product(" + productId + ", " + name + ", price=" + price + ", stock=" + stock + ")";
    }
}