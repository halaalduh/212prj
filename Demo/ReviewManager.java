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

    private LinkedList<Reviews> reviewList = new LinkedList<>();

    public ReviewManager(String fileName) {
        try {
            File f = new File(fileName);
            Scanner in = new Scanner(f);
            in.nextLine(); // skip header

            while (in.hasNext()) {
                String line = in.nextLine();
                String[] data = line.split(",");

                int rid = Integer.parseInt(data[0]);
                int pid = Integer.parseInt(data[1]);
                int cid = Integer.parseInt(data[2]);
                int rating = Integer.parseInt(data[3]);
                String comment = data[4];

                reviewList.insert(new Reviews(rid, pid, cid, rating, comment));
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LinkedList<Reviews> getReviews() {
        return reviewList;
    }

    // ✅ Compute Average Rating for One Product
    public double computeAvgRating(int productId) {
        if (reviewList.empty()) return 0;

        double sum = 0;
        int count = 0;

        reviewList.findFirst();
        while (true) {
            if (reviewList.retrieve().getProductId() == productId) {
                sum += reviewList.retrieve().getRating();
                count++;
            }
            if (reviewList.last()) break;
            reviewList.findNext();
        }

        return (count == 0) ? 0 : (sum / count);
    }

    // ✅ Add New Review
    public boolean AddReview(int productId, int customerId) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter new review ID: ");
        int rid = in.nextInt();

        if (searchReviewById(rid) != null) {
            System.out.println("Review ID already exists.");
            return false;
        }

        System.out.print("Enter rating (1-5): ");
        int rating = in.nextInt();
        while (rating < 1 || rating > 5) {
            System.out.print("Invalid rating. Enter (1–5): ");
            rating = in.nextInt();
        }
        in.nextLine();

        System.out.print("Enter comment: ");
        String comment = in.nextLine();

        Reviews r = new Reviews(rid, productId, customerId, rating, comment);
        reviewList.insert(r);

        System.out.println("Review added successfully.");
        return true;
    }

    // ✅ Search Review by ID
    public Reviews searchReviewById(int rid) {
        if (reviewList.empty()) return null;

        reviewList.findFirst();
        while (!reviewList.last()) {
            if (reviewList.retrieve().getReviewId() == rid)
                return reviewList.retrieve();
            reviewList.findNext();
        }
        if (reviewList.retrieve().getReviewId() == rid)
            return reviewList.retrieve();

        return null;
    }

    // ✅ Edit Review (rating or comment or both)
    public boolean updateReview(int reviewId, int newRating, String newComment) {
        Reviews r = searchReviewById(reviewId);
        if (r == null) {
            System.out.println("Review not found.");
            return false;
        }

        if (newRating >= 1 && newRating <= 5)
            r.setRating(newRating);

        if (newComment != null && !newComment.isBlank())
            r.setComment(newComment);

        System.out.println("Review updated successfully.");
        return true;
    }

    // ✅ Find Common Reviewed Products with Avg Rating ≥ 4
    public void commonReviewedProducts(int cid1, int cid2, ProductManager productManager) {

        // Build list of product IDs reviewed by first customer
        LinkedList<Integer> list1 = new LinkedList<>();
        fillReviewedProducts(cid1, list1);

        // Build list of product IDs reviewed by second customer
        LinkedList<Integer> list2 = new LinkedList<>();
        fillReviewedProducts(cid2, list2);

        boolean found = false;

        list1.findFirst();
        while (true) {int productId = list1.retrieve();

            if (contains(list2, productId) && computeAvgRating(productId) >= 4) {
                Products p = productManager.searchProductById(productId);
                if (p != null) {
                    System.out.println(p + " | Avg Rating: " + computeAvgRating(productId));
                    found = true;
                }
            }

            if (list1.last()) break;
            list1.findNext();
        }

        if (!found)
            System.out.println("No common highly rated products found.");
    }

    // helper → fill list with unique reviewed product ids
    private void fillReviewedProducts(int cid, LinkedList<Integer> dest) {

        reviewList.findFirst();
        while (true) {
            if (reviewList.retrieve().getCustomerId() == cid) {
                int pid = reviewList.retrieve().getProductId();
                if (!contains(dest, pid)) {
                    if (dest.empty()) dest.insert(pid);
                    else { dest.findFirst(); dest.insert(pid); }
                }
            }
            if (reviewList.last()) break;
            reviewList.findNext();
        }
    }

    // helper → check existence in linked list
    private boolean contains(LinkedList<Integer> list, int val) {
        if (list.empty()) return false;

        list.findFirst();
        while (true) {
            if (list.retrieve() == val) return true;
            if (list.last()) break;
            list.findNext();
        }
        return false;
    }
}