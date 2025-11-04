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
      int productId;
    String name;
    double price;
    int stock;
    DoubleLinkedList<Reviews> reviews = new DoubleLinkedList<>();

    public Products() {
        this.productId = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
    }

    public Products (int pid, String n, double p, int s) {
        this.productId = pid;
        this.name = n;
        this.price = p;
        this.stock = s;
    }

    // Getters and setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public void addStock(int stock) { this.stock += stock; }
    public void removeStock(int stock) { this.stock -= stock; }

    // Review handling
    public void addReview(Reviews r) { reviews.insert(r); }

    public boolean removeReview(Reviews r) {
        if (!reviews.empty()) {
            reviews.findFirst();
            while (!reviews.last()) {
                if (reviews.retrieve().equals(r)) {
                    reviews.remove();
                    return true;
                }
                reviews.findNext();
            }
            if (reviews.retrieve().equals(r)) {
                reviews.remove();
                return true;
            }
        }
        return false;
    }

    public DoubleLinkedList<Reviews> getReviews() { return reviews; }

    @Override
    public String toString() {
        String str = "\nProduct{" + "id=" + productId + ", name=" + name +", price=" + price +", stock=" + stock;
        if (!reviews.empty()) {
            str += ", reviews=[";
            reviews.findFirst();
            while (!reviews.last()) {
                str += reviews.retrieve() + " ";
                reviews.findNext();
            }
            str += reviews.retrieve() + "]";
        }
        str += "}";
        return str;
    }
}