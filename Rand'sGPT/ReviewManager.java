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
public class ReviewManager {

    public ReviewManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); // skip header
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int rid = Integer.parseInt(data[0]);
                int pid = Integer.parseInt(data[1]);
                int cid = Integer.parseInt(data[2]);
                int rating = Integer.parseInt(data[3]);
                String comment = data[4];
                reviews.insert(new Reviews(rid, pid, cid, rating, comment));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DoubleLinkedList<Reviews> getReviews() { return reviews; }

    public Reviews addReview(int rid, int pid, int cid, int rating, String comment) {
        if (checkReviewId(rid)) return null;
        Reviews r = new Reviews(rid, pid, cid, rating, comment);
        reviews.insert(r);
        return r;
    }

    public void updateReview(int rid, int newRating, String newComment) {
        if (reviews.empty()) return;
        reviews.findFirst();
        while (!reviews.last()) {
            if (reviews.retrieve().getReviewId() == rid) {
                if (newRating > 0) reviews.retrieve().setRating(newRating);
                if (newComment != null) reviews.retrieve().setComment(newComment);
                return;
            }
            reviews.findNext();
        }
        if (reviews.retrieve().getReviewId() == rid) {
            if (newRating > 0) reviews.retrieve().setRating(newRating);
            if (newComment != null) reviews.retrieve().setComment(newComment);
        }
    }

    public boolean checkReviewId(int rid) {
        if (reviews.empty()) return false;
        reviews.findFirst();
        while (!reviews.last()) {
            if (reviews.retrieve().getReviewId() == rid) return true;
            reviews.findNext();
        }
        return (reviews.retrieve().getReviewId() == rid);
    }

    // Common products reviewed by two customers with avg rating > 4
    public void commonReviewedProducts(int c1, int c2, ProductManager pm) {
        DoubleLinkedList<Integer> productsC1 = new DoubleLinkedList<>();
        DoubleLinkedList<Integer> productsC2 = new DoubleLinkedList<>();

        reviews.findFirst();
        while (!reviews.last()) {
            if (reviews.retrieve().getCustomerID() == c1) productsC1.insert(reviews.retrieve().getProductID());
            if (reviews.retrieve().getCustomerID() == c2) productsC2.insert(reviews.retrieve().getProductID());
            reviews.findNext();
        }
        if (reviews.retrieve().getCustomerID() == c1) productsC1.insert(reviews.retrieve().getProductID());
        if (reviews.retrieve().getCustomerID() == c2) productsC2.insert(reviews.retrieve().getProductID());

        System.out.println("Common products with avg > 4:");
        productsC1.findFirst();
        while (!productsC1.last()) {
            int pid = productsC1.retrieve();
            if (contains(productsC2, pid)) {
                Products p = pm.searchProductById(pid);
                if (p != null && p.avgRating() > 4) System.out.println(p);
            }
            productsC1.findNext();
        }
        int pid = productsC1.retrieve();
        if (contains(productsC2, pid)) {
            Products p = pm.searchProductById(pid);
            if (p != null && p.avgRating() > 4) System.out.println(p);
        }
    }

    private boolean contains(DoubleLinkedList<Integer> list, int val) {
        if (list.empty()) return false;
        list.findFirst();
        while (!list.last()) {
            if (list.retrieve() == val) return true;
            list.findNext();
        }
        return (list.retrieve() == val);
    }

}

///haifa new

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg212prj;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderManager {
     private DoubleLinkedList<Orders> orders = new DoubleLinkedList<>();

    public OrderManager(String fileName) {
        // reads it in one line so split is needed between (,)
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); // skip header of table
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int oid = Integer.parseInt(data[0]);
                int cid = Integer.parseInt(data[1]);
                // added we have to check the run //
                
                   String  pp =  data[2].replaceAll("\"", "");
                    String [] p = pp.split(";");
                    Integer [] pids = new Integer [p.length];
                    for ( int i = 0 ; i< pids.length ; i++)
                            pids[i] = new Integer(Integer.parseInt(p[i]);
                
           
                ///////////////////////////
                
                double price = Double.parseDouble(data[3]);
                String date = data[4];
                String status = data[5]; // we have 6
                ////////////////////////////////
                
                 Order order = new Order(oid, cid, pids, price, date, status ); //pids is added
                orders.insert(order);
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DoubleLinkedList<Orders> getOrders() { return orders; } // returns link not copy 

    public Orders searchOrderById(int id) {
        if (orders.empty()) return null;
        
        orders.findFirst();
        while (!orders.last()) {
            if (orders.retrieve().getOrderId() == id) return orders.retrieve();
            orders.findNext();
        }
        if (orders.retrieve().getOrderId() == id) return orders.retrieve();
        return null;
    }

    public boolean cancelOrder(int id) {
        Orders o = searchOrderById(id);
        if (o == null) return false;
        if (o.getStatus().equalsIgnoreCase("cancelled")) return false;
        o.setStatus("cancelled");
        return true; // do we have to insert the order we canceled to list?
    }

    public boolean updateOrderStatus(int id, String newStatus) { ///?
        Orders o = searchOrderById(id);
        if (o == null) return false;
        if (o.getStatus().equalsIgnoreCase("cancelled")) return false;
        o.setStatus(newStatus);
        return true;
    }

    // Orders between two dates
    public DoubleLinkedList<Orders> ordersBetweenDates(String d1, String d2) {
        DoubleLinkedList<Orders> result = new DoubleLinkedList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(d1, fmt);
        LocalDate end = LocalDate.parse(d2, fmt);

        if (orders.empty()) return result;
        orders.findFirst();
        while (!orders.last()) {
            if (orders.retrieve().getDate().isAfter(start) && orders.retrieve().getDate().isBefore(end))
                result.insert(orders.retrieve());
            orders.findNext();
        }
        if (orders.retrieve().getDate().isAfter(start) && orders.retrieve().getDate().isBefore(end))
            result.insert(orders.retrieve());
        return result;
    }
      public boolean checkOrderID(int oid) /// added if found or not maybe used in main
    {
        if (!orders.empty())
        {
            orders.findFirst();
            while (!orders.last())
            {
                if (orders.retrieve().getOrderId()== oid)
                    return true;
                orders.findNext();
            }
            if (orders.retrieve().getOrderId()== oid)
                return true;
        }
        return false;
    }
}
