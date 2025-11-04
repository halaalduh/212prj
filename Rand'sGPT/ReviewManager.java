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



import java.io.File;
import java.util.Scanner;


public class ReviewManager {
    public static Scanner input = new Scanner (System.in);
    public static DoubleLinkedList<Reviews> reviews = new DoubleLinkedList<Reviews> (); // addedd 
    
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
    
 public Review AddReview(int cID, int pID)
    {
     System.out.println("Enter Review ID :");
        int reviewID = input.nextInt();
        
        while (checkReviewID(reviewID))
        {
            System.out.println("Re- Enter Review ID, already available :");
            reviewID = input.nextInt();
        }        
        
        System.out.println("Enter rating (5..1): ");
        int rate = input.nextInt();
        while ( rate >5 || rate <1)
        {
            System.out.println("Re-Enter rating (5..1) :");
            rate = input.nextInt();
        }

        System.out.println("Enter comment: ");
        String comment = input.nextLine();
        comment = input.nextLine();

        Review review = new Review (reviewID, pID, cID, rate,  comment );
        reviews.findFirst();
        reviews.insert(review);
        return review; /// changed bec it shoud takes inputs 
    }

 public void updateReview(int rid, int newRating, String newComment) { /// constructor is diff and printing and inputs
     ///verrrry important to check it has menu
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

    // Common products reviewed by two customers with avg rating > 4  ??????????
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

    private boolean contains(DoubleLinkedList<Integer> list, int val) //?????????? {
        if (list.empty()) return false;
        list.findFirst();
        while (!list.last()) {
            if (list.retrieve() == val) return true;
            list.findNext();
        }
        return (list.retrieve() == val);
    }

}

