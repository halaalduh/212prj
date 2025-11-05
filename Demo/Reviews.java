/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;

/**
 *
 * @author randd
 */
public class Reviews {
  
    private int reviewId;
    private int productId;
    private int customerId;
    private int rating;      // 1..5
    private String comment;

    public Reviews(int rid, int pid, int cid, int rating, String comment) {
        this.reviewId = rid;
        this.productId = pid;
        this.customerId = cid;
        this.rating = rating;
        this.comment = comment;
    }

    // getters used by managers
    public int getReviewId() { return reviewId; }
    public int getProductId() { return productId; }
    public int getCustomerId() { return customerId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }

    // setters used by ReviewManager
    public void setRating(int r) { this.rating = r; }
    public void setComment(String c) { this.comment = c; }

    @Override public String toString() {
        return "Review(" + reviewId + ", product=" + productId +
               ", customer=" + customerId + ", rating=" + rating +
               ", comment=\"" + comment + "\")";
    }
    }