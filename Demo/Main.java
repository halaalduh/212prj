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

    private static final Scanner in = new Scanner(System.in);

    // Managers
    private static final ProductManager products = new ProductManager("prodcuts.csv");
    private static final CustomerManager customers = new CustomerManager("customers.csv");
    private static final OrderManager orders = new OrderManager("orders.csv");
    private static final ReviewManager reviews = new ReviewManager("reviews.csv");

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n==========================================");
            System.out.println("         Store Management System          ");
            System.out.println("==========================================");
            System.out.println("1) Products");
            System.out.println("2) Customers");
            System.out.println("3) Orders");
            System.out.println("4) Reviews");
            System.out.println("5) Exit");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                    productsMenu();
                    break;
                case 2:
                    customersMenu();
                    break;
                case 3:
                    ordersMenu();
                    break;
                case 4:
                    reviewsMenu();
                    break;
                case 5:
                    System.out.println("\nThank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }

    //===================== PRODUCTS MENU =====================
    private static void productsMenu() {

        System.out.println("\n------ PRODUCTS MENU ------");
        System.out.println("1) Add New Product");
        System.out.println("2) Remove Product (Set Stock = 0)");
        System.out.println("3) Update Product (Name / Price / Stock)");
        System.out.println("4) Search Product by Name");
        System.out.println("5) Search Product by ID");
        System.out.println("6) Show Out-of-Stock Products");
        System.out.println("7) Show Top 3 Products by Average Rating");
        System.out.println("8) Back");
        System.out.print("Enter choice: ");

        int ch = in.nextInt();

        switch (ch) {
            case 1:
                products.addProduct();
                break;
            case 2:
                System.out.print("Enter Product ID: ");
                products.removeProduct(in.nextInt());
                break;
            case 3:
                products.updateProduct();
                break;
            case 4:
                System.out.print("Enter Product Name: ");
                products.searchProductByName(in.next());
                break;
            case 5:
                System.out.print("Enter Product ID: ");
                products.searchProductById(in.nextInt());
                break;
            case 6:
                products.printOutOfStock();
                break;
            case 7:
                products.top3ProductsByRating();
                break;
            case 8:
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    //===================== CUSTOMERS MENU =====================
    private static void customersMenu() {

        System.out.println("\n------ CUSTOMERS MENU ------");
        System.out.println("1) Register New Customer");
        System.out.println("2) Show Customer Order History");
        System.out.println("3) Back");
        System.out.print("Enter choice: ");

        int ch = in.nextInt();

        switch (ch) {
            case 1:
                customers.registerCustomer();
                break;
            case 2:
                System.out.print("Enter Customer ID: ");
                customers.printOrderHistory(in.nextInt());
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    //===================== ORDERS MENU =====================
    private static void ordersMenu() {

        System.out.println("\n------ ORDERS MENU ------");
        System.out.println("1) Search Order by ID");
        System.out.println("2) Cancel Order");
        System.out.println("3) Update Order Status");
        System.out.println("4) Show Orders Between Dates (dd/MM/yyyy)");
        System.out.println("5) Back");
        System.out.print("Enter choice: ");

        int ch = in.nextInt();

        switch (ch) {
            case 1:
                System.out.print("Enter Order ID: ");
                orders.searchOrderById(in.nextInt());
                break;
            case 2:
                System.out.print("Enter Order ID: ");
                orders.cancelOrder(in.nextInt());
                break;
            case 3:
                System.out.print("Enter Order ID: ");
                int oid = in.nextInt();
                System.out.print("Enter New Status: ");
                orders.updateOrderStatus(oid, in.next());
                break;
            case 4:
                System.out.print("Start date (dd/MM/yyyy): ");
                String d1 = in.next();
                System.out.print("End date (dd/MM/yyyy): ");
                String d2 = in.next();
                orders.ordersBetweenDates(d1, d2);
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    //===================== REVIEWS MENU =====================
    private static void reviewsMenu() {

        System.out.println("\n------ REVIEWS MENU ------");
        System.out.println("1) Add Review");
        System.out.println("2) Edit Review");
        System.out.println("3) Show Common Reviewed Products (Rating ≥ 4)");
        System.out.println("4) Back");
        System.out.print("Enter choice: ");

        int ch = in.nextInt();
        switch (ch) {
            case 1:
                System.out.print("Product ID: ");
                int pid = in.nextInt();
                System.out.print("Customer ID: ");
                int cid = in.nextInt();
                reviews.AddReview(pid, cid);
                break;

            case 2:
                System.out.print("Review ID: ");
                int rid = in.nextInt();
                System.out.print("New Rating (0 = no change): ");
                int rating = in.nextInt();
                in.nextLine();
                System.out.print("New Comment (leave blank = no change): ");
                String cmt = in.nextLine();
                reviews.updateReview(rid, rating, cmt.isBlank() ? null : cmt);
                break;

            case 3:
                System.out.print("First Customer ID: ");
                int c1 = in.nextInt();
                System.out.print("Second Customer ID: ");
                int c2 = in.nextInt();
                reviews.commonReviewedProducts(c1, c2, products);
                break;

            case 4:
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
}