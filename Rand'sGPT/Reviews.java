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
     int reviewId;
    int productID;
    int customerID;
    int rating;   // 1–5
    String comment;

    public Reviews() {
        this.reviewId = 0;
        this.productID = 0;
        this.customerID = 0;
        this.rating = 0;
        this.comment = "";
    }

    public Reviews(int reviewId, int product, int customer, int rating, String comment) {
        this.reviewId = reviewId;
        this.productID = product;
        this.customerID = customer;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters
    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + reviewId +
                ", product=" + productID +
                ", customer=" + customerID +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }}
