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
public class ProductManager {
     private DoubleLinkedList<Products> products = new DoubleLinkedList<>();

    // Load products from CSV
    public ProductManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); // skip header
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int pid = Integer.parseInt(data[0]);
                String name = data[1].trim();
                double price = Double.parseDouble(data[2]);
                int stock = Integer.parseInt(data[3]);
                products.insert(new Products(pid, name, price, stock));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DoubleLinkedList<Products> getProducts() { return products; }

    // Search product by ID
    public Products searchProductById(int productID) {
        if (products.empty()) return null;
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getProductId() == productID) return products.retrieve();
            products.findNext();
        }
        if (products.retrieve().getProductId() == productID) return products.retrieve();
        return null;
    }

    // Search product by name
    public Products searchProductByName(String name) {
        if (products.empty()) return null;
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getName().equalsIgnoreCase(name)) return products.retrieve();
            products.findNext();
        }
        if (products.retrieve().getName().equalsIgnoreCase(name)) return products.retrieve();
        return null;
    }

    // Print out-of-stock products
    public void printOutOfStock() {
        if (products.empty()) return;
        products.findFirst();
        while (!products.last()) {
            if (products.retrieve().getStock() == 0) System.out.println(products.retrieve());
            products.findNext();
        }
        if (products.retrieve().getStock() == 0) System.out.println(products.retrieve());
    }

    // Top 3 products by average rating
    public void top3ProductsByRating() {
        if (products.empty()) {
            System.out.println("No products available");
            return;
        }
        Product[] top = new Product[3];
        double[] ratings = {-1, -1, -1};

        products.findFirst();
        while (!products.last()) {
            updateTop(products.retrieve(), top, ratings);
            products.findNext();
        }
        updateTop(products.retrieve(), top, ratings);

        System.out.println("Top 3 Products by Rating:");
        for (Products p : top) if (p != null) System.out.println(p + " Avg=" + p.avgRating());
    }

    private void updateTop(Products p, Products[] top, double[] ratings) {
        double avg = p.avgRating();
        for (int i = 0; i < 3; i++) {
            if (avg > ratings[i]) {
                for (int j = 2; j > i; j--) {
                    ratings[j] = ratings[j-1];
                    top[j] = top[j-1];
                }
                ratings[i] = avg;
                top[i] = p;
                break;
            }
        }
    }

}







//halaaaaaaaa




import java.io.File;
import java.util.Scanner;
public class ProductManager {
     private DoubleLinkedList<Products> product = new DoubleLinkedList<>();

    // Load products from CSV
    public ProductManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); // skip header
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int pid = Integer.parseInt(data[0]);
                String name = data[1].trim();
                double price = Double.parseDouble(data[2]);
                int stock = Integer.parseInt(data[3]);
                product.insert(new Products(pid, name, price, stock));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DoubleLinkedList<Products> getProducts() { return product; }

    
    
    // Search product by name
public Products searchProductByName(String name) {
    if (product.empty()) {
        System.out.println("No products found.");
        return null;
    }
    product.findFirst();
    while (!product.last()) {
        Products p = product.retrieve();
        if (p.getName().equalsIgnoreCase(name)) {
            System.out.println("Product found: " + p);
            return p;
        }
        product.findNext();
    }
    Products last = product.retrieve();
    if (last.getName().equalsIgnoreCase(name)) {
        System.out.println("Product found: " + last);
        return last;
    }
    System.out.println("Product with name \"" + name + "\" not found.");
    return null;
}


    
   // Search product by ID
public Products searchProductById(int pID) {
    if (product.empty()) {
        System.out.println("No products found.");
        return null;
    }
    product.findFirst();
    while (!product.last()) {
        Products p = product.retrieve();
        if (p.getProductId() == pID) {
            System.out.println("Product found: " + p);
            return p;
        }
        product.findNext();
    }
    Products last = product.retrieve();
    if (last.getProductId() == pID) {
        System.out.println("Product found: " + last);
        return last;
    }

    System.out.println("Product with ID " + pID + " not found.");
    return null;
}



    // Print out-of-stock products
    public void printOutOfStock() {
        if (product.empty()){
             System.out.println("No products available.");
            return;}
        product.findFirst();
        while (!product.last()) {
            if (product.retrieve().getStock() == 0)
                System.out.println("Out of stock: "+ product.retrieve());
            product.findNext();
        }
        if (product.retrieve().getStock() == 0) 
            System.out.println(product.retrieve());
    }

    
    
    
    // Top 3 products by average rating
    public void top3ProductsByRating() {
        if (product.empty()) {
            System.out.println("No products available");
            return;
        }
       Products[] top = new Products[3];
        double[] ratings = {-1, -1, -1};
        product.findFirst();
        while (!product.last()) {
            updateTop(product.retrieve(), top, ratings);
            product.findNext();
        }
        updateTop(product.retrieve(), top, ratings);

        System.out.println("Top 3 Products by Rating:");
        for (Products p : top) if (p != null) System.out.println(p + " Avg=" + p.avgRating());
    }

    
  // Updates the top 3 products based on their average rating  
 private void updateTop(Products p, Products[] top, double[] ratings) {
    if (p == null) return;             
    double avg = p.avgRating();  
    System.out.println("Checking product: " + p.getName() + " Average Rating: " + avg);
    for (int i = 0; i < 3; i++) {
        if (avg > ratings[i]) {
            for (int j = 2; j > i; j--) {
                ratings[j] = ratings[j - 1];
                top[j] = top[j - 1];
            }
            ratings[i] = avg;
            top[i] = p;
            break;
        }
    }
 }
}



