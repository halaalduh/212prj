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
