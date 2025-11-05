package proj;


import java.io.File;
import java.util.Scanner;

public class ProductManager {

    private LinkedList<Products> productList = new LinkedList<>();
    private Scanner in=new Scanner(System.in);
    private ReviewManager reviewManager; // used to compute avg ratings

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

                productList.insert(new Products(pid, name, price, stock));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Link with review manager (needed for avg rating and top3)
    public void connect(ReviewManager r) {
        this.reviewManager = r;
    }

    public LinkedList<Products> getProducts() {
        return productList;
    }

    // SEARCH BY ID
    public Products searchProductById(int id) {
        if (productList.empty()) return null;

        productList.findFirst();
        while (!productList.last()) {
            if (productList.retrieve().getProductId() == id)
                return productList.retrieve();
            productList.findNext();
        }
        if (productList.retrieve().getProductId() == id)
            return productList.retrieve();

        System.out.println("No product found.");
        return null;
    }

    // SEARCH BY NAME
    public Products searchProductByName(String name) {
        if (productList.empty()) return null;

        productList.findFirst();
        while (!productList.last()) {
            if (productList.retrieve().getName().equalsIgnoreCase(name))
                return productList.retrieve();
            productList.findNext();
        }
        if (productList.retrieve().getName().equalsIgnoreCase(name))
            return productList.retrieve();

        System.out.println("No product found.");
        return null;
    }
    
    public void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = in.nextInt();
        if ( searchProductById(id) != null) {
            System.out.println("ID exists.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = in.next();
        System.out.print("Enter Price: ");
        double price = in.nextDouble();
        System.out.print("Enter Stock: ");
        int stock = in.nextInt();

        productList.insert(new Products(id, name, price, stock));
        System.out.println("Product added.");
    }

    // REQUIRED BY MAIN (quick fix)
    public void removeProduct(int id) {
        Products p =  searchProductById(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }
        p.setStock(0);
        System.out.println("Product stock set to 0.");}

    // PRINT OUT-OF-STOCK PRODUCTS
    public void printOutOfStock() {
        if (productList.empty()) {
            System.out.println("No products available.");
            return;
        }

        boolean found = false;
        productList.findFirst();
        while (!productList.last()) {
            if (productList.retrieve().getStock() == 0) {
                System.out.println(productList.retrieve());
                found = true;
            }
            productList.findNext();
        }
        if (productList.retrieve().getStock() == 0) {
            System.out.println(productList.retrieve());
            found = true;
        }

        if (!found) System.out.println("No products are out of stock.");
    }

    public void updateProduct() {
        System.out.println("(Quick version) Update Product:");
        System.out.print("Enter Product ID: ");
        int id = in.nextInt();
        Products p = searchProductById(id);
        if (p == null) {
            System.out.println("Not found.");
            return;
        }
        System.out.print("New Price: ");
        p.setPrice(in.nextDouble());
        System.out.println("Updated.");}
    
    
    // decrease stock by 1 and return success/failure
    public boolean reduceStock(int productId) {
        Products p = searchProductById(productId);
        if (p == null) return false;
        if (p.getStock() == 0) return false;

        p.setStock(p.getStock() - 1);
        return true;
    }

    // ✅ REQUIRED WHEN ORDER IS CANCELLED
    public void restoreStock(int productId) {
        Products p = searchProductById(productId);
        if (p != null)
            p.setStock(p.getStock() + 1);
    }

    //  TOP 3 PRODUCTS BY AVERAGE RATING
    public void top3ProductsByRating() {
        if (productList.empty()) return;

        LinkedPQ<Products> pq = new LinkedPQ<>();

        productList.findFirst();
        while (true) {
            Products p = productList.retrieve();
            double avg = reviewManager.computeAvgRating(p.getProductId());
            pq.enqueue(p, avg);
            if (productList.last()) break;
            productList.findNext();
        }
        System.out.println("\nTop 3 Products by Rating:");
        for (int i = 1; i <= 3 && pq.length() > 0; i++) {
            PQElement<Products> top = pq.serve();
            System.out.println(i + ". " + top.data.getName() +
                               " (ID: " + top.data.getProductId() +
                               ") Avg Rating: " + top.priority);
        }
    }
}