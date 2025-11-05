public class Products {
    int productId;
    String name;
    double price;
    int stock;
    LinkedList<Reviews> reviews = new LinkedList<>();

    public Products() {
        this.productId = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
    }

    public Products (int proId, String n, double p, int s) {
        this.productId = proId;
        this.name = n;
        this.price = p;
        this.stock = s;
    }

    // Getters
    public int getProductId() {
        return productId; }
    public void setProductId(int productId) { 
        this.productId = productId; }
    public String getName() { 
        return name; 
    }
    public int getStock() {
        return stock; 
    }
     public double getPrice() {
        return price; 
     }
     public LinkedList<Reviews> getReviews() {
        return reviews; 
     }
     
    //Setters
    public void setName(String name) {
        this.name = name; 
    }
    public void setPrice(double price) {
        this.price = price; 
    }
    public void setStock(int stock) {
        this.stock = stock; 
    }
 
    
    // Increases stock by the given amount
    public void addStock(int stock) {
        this.stock += stock; 
    }
    
    // Decreases stock by the given amount
    public void removeStock(int stock) {
        this.stock -= stock; 
    }

    // Adds a review 
    public void addReview(Reviews r) {
        reviews.insert(r); }

     // Removes a specific review
    public boolean removeReview(Reviews rev) {
        if (!reviews.empty()) {
            reviews.findFirst();
            while (!reviews.last()) {
                if (reviews.retrieve().equals(rev)) {
                    reviews.remove();
                    System.out.println("Review removed successfully.");
                    return true;
                }
                reviews.findNext();
            }
            if (reviews.retrieve().equals(rev)) {
                reviews.remove();
                System.out.println("Review removed successfully.");
                return true;
            }
        }
         System.out.println("Review not found.");
        return false;
    }
    
    // Calculates and returns the average rating of the product
    public double avgRating() {
    if (reviews == null || reviews.empty()) {
        return 0;
    }
    double total = 0;
    int count = 0;
    reviews.findFirst();
    while (!reviews.last()) {
        total = total +reviews.retrieve().getRating();
        count++;
        reviews.findNext();
    }
    total= total +reviews.retrieve().getRating();
    count++;

    if (count > 0) {
        double average = total / count;
        System.out.println("Average rating = " + average);
        return average;
    } else {
        return 0.0;
    }
}

   @Override
public String toString() {
    String str = "Product{" 
            + "id=" + productId 
            + ", name=" + name 
            + ", price=" + price 
            + ", stock=" + stock;

    if (!reviews.empty()) {
        str += " (reviews: ";
        reviews.findFirst();
        while (!reviews.last()) {
            str += reviews.retrieve() + " ";
            reviews.findNext();
        }
        str += reviews.retrieve() + ")";
    }

    str += " }";
    return str;
}
}
