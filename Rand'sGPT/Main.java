/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proj;

/**
 *
 * @author randd
 */
import java.util.Scanner;
public class Main {
    private static final Scanner input = new Scanner(System.in);

    // Managers
    private static final ProductManager productManager = new ProductManager("products.csv");
    private static final CustomerManager custManager = new CustomerManager("customers.csv");
    private static final OrderManager orderManager = new OrderManager("orders.csv");
    private static final ReviewManager reviewManager = new ReviewManager("reviews.csv");

    // ==============================
    // Main Menu
    // ==============================
    private static int mainMenu() {
        System.out.println("\n=== Store Management ===");
        System.out.println("1. Products");
        System.out.println("2. Customers");
        System.out.println("3. Orders");
        System.out.println("4. Reviews");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
        return input.nextInt();
    }

    // ==============================
    // Products
    // ==============================
    private static void productsMenu() {
        System.out.println("\n--- Products Menu ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Out-of-stock products");
        System.out.println("4. Top 3 products by rating");
        System.out.println("5. Back");
        System.out.print("Choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1 :{
                System.out.print("Enter ID: ");
                int pid = input.nextInt();
                Products p = productManager.searchProductById(pid);
                System.out.println(p != null ? p : "Not found.");
            }
            case 2 :{
                System.out.print("Enter name: ");
                String name = input.next();
                Products p = productManager.searchProductByName(name);
                System.out.println(p != null ? p : "Not found.");
            }
            case 3 : productManager.printOutOfStock();
            case 4 : productManager.top3ProductsByRating();
            case 5 : break;
            default : System.out.println("Invalid option");
        }
    }

    // ==============================
    // Customers
    // ==============================
    private static void customersMenu() {
        System.out.println("\n--- Customers Menu ---");
        System.out.println("1. Register new customer");
        System.out.println("2. Show order history");
        System.out.println("3. Back");
        System.out.print("Choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1 : custManager.registerCustomer();
            case 2 : {
                System.out.print("Customer ID: ");
                int cid = input.nextInt();
                custManager.printOrderHistory(cid);
            }
            case 3 :break;
            default : System.out.println("Invalid option");
        }
    }

    // ==============================
    // Orders
    // ==============================
    private static void ordersMenu() {
        System.out.println("\n--- Orders Menu ---");
        System.out.println("1. Search order by ID");
        System.out.println("2. Cancel order");
        System.out.println("3. Update order status");
        System.out.println("4. Orders between two dates");
        System.out.println("5. Back");
        System.out.print("Choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1 : {
                System.out.print("Order ID: ");
                int oid = input.nextInt();
                Orders o = orderManager.searchOrderById(oid);
                System.out.println(o != null ? o : "Not found.");
            }
            case 2:{
                System.out.print("Order ID: ");
                int oid = input.nextInt();
                boolean cancelled = orderManager.cancelOrder(oid);
                System.out.println(cancelled ? "Order cancelled." : "Cancel failed.");}
            case 3 : {
                System.out.print("Order ID: ");
                int oid = input.nextInt();
                System.out.print("New status: ");
                String status = input.next();
                boolean updated = orderManager.updateOrderStatus(oid, status);
                System.out.println(updated ? "Updated." : "Update failed.");}
            case 4 : {
                System.out.print("Start date (dd/MM/yyyy): ");
                String d1 = input.next();
                System.out.print("End date (dd/MM/yyyy): ");
                String d2 = input.next();
               // var list = orderManager.ordersBetweenDates(d1, d2);
                if (list.empty()) {
                    System.out.println("No orders in range.");
                } else {
                    list.findFirst();
                    while (!list.last()) {
                        System.out.println(list.retrieve());
                        list.findNext();
                    }
                    System.out.println(list.retrieve());
                }
            }
            case 5 : break;
            default : System.out.println("Invalid option");
        }
    }

    // ==============================
    // Reviews
    // ==============================
    private static void reviewsMenu() {
        System.out.println("\n--- Reviews Menu ---");
        System.out.println("1. Add review");
        System.out.println("2. Edit review");
        System.out.println("3. Common reviewed products (rating > 4)");
        System.out.println("4. Back");
        System.out.print("Choice: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1 :{
                System.out.print("Review ID: ");
                int rid = input.nextInt();
                System.out.print("Product ID: ");
                int pid = input.nextInt();
                System.out.print("Customer ID: ");
                int cid = input.nextInt();
                System.out.print("Rating (1–5): ");
                int rating = input.nextInt();
                input.nextLine();
                System.out.print("Comment: ");
                String comment = input.nextLine();
                Reviews r = reviewManager.addReview(rid, pid, cid, rating, comment);
                System.out.println(r != null ? "Added: " + r : "Review ID already exists.");
            }
            case 2 : {
                System.out.print("Review ID: ");
                int rid = input.nextInt();
                System.out.print("New rating (0 if no change): ");
                int rating = input.nextInt();
                input.nextLine();
                System.out.print("New comment (leave blank if no change): ");
                String comment = input.nextLine();
                reviewManager.updateReview(rid, rating, comment.isBlank() ? null : comment);
            }
            case 3 : {
                System.out.print("First customer ID: ");
                int c1 = input.nextInt();
                System.out.print("Second customer ID: ");
                int c2 = input.nextInt();
                reviewManager.commonReviewedProducts(c1, c2, productManager);
            }
            case 4 : {}
            default :System.out.println("Invalid option");
        }
    }

    // ==============================
    // Main Runner
    // ==============================
    public static void main(String[] args) {
        int choice;
        do {
            choice = mainMenu();
            switch (choice) {
                case 1 : productsMenu();
                case 2 : customersMenu();
                case 3 : ordersMenu();
                case 4 : reviewsMenu();
                case 5 : System.out.println("Goodbye!");
            }
        } while (choice != 5);
    }
}

