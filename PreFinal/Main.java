import java.util.Scanner;
import java.util.Scanner;



public class Main {

    private static final Scanner in = new Scanner(System.in);

    // managers
    private static final ProductManager products = new ProductManager("products.csv");
    private static final CustomerManager customers = new CustomerManager("customers.csv");
    private static final OrderManager orders = new OrderManager("orders.csv");
    private static final ReviewManager reviews = new ReviewManager("reviews.csv");

    public static void main(String[] args) {
        int choice;
        do {
            
            System.out.println("\n  store management system ");
        System.out.println("1- products");
        System.out.println("2- customers");
        System.out.println("3- orders");
        System.out.println("4- reviews");
        System.out.println("5- exit");
        System.out.print("enter your choice: ");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    productsmenu();
                    break;
                case 2:
                    customersmenu();
                    break;
                case 3:
                    ordersmenu();
                    break;
                case 4:
                    reviewsmenu();
                    break;
                case 5:
                    System.out.println("thanks for using the system. goodbye!");
                    break;
                default:
                    System.out.println("invalid choice. try again.");
            }
        } while (choice != 5);
    }

   

    private static void productsmenu() {
        System.out.println("\n   products menu ");
        System.out.println("1- search by Name");
        System.out.println("2- search by Id");
        System.out.println("3- show out of stock products");
        System.out.println("4- show top 3 by rating");
        System.out.println("5- back");
        System.out.print("enter choice: ");
        int ch1 = in.nextInt();

        switch (ch1) {
            case 1:
                System.out.print("enter product name: ");
                String pname = in.next();
                products.searchProductByName(pname);
                break;

            case 2:
                System.out.print("enter product id: ");
                int pid = in.nextInt();
                products.searchProductById(pid);
                break;

            case 3:
                System.out.println("checking out-of-stock products:");
                products.printOutOfStock();
                break;

            case 4:
                System.out.println("calculating top 3 rated products:");
                products.top3ProductsByRating();
                break;

            case 5:
                System.out.println("returning to main menu:");
                break;

            default:
                System.out.println("invalid option.");
        }
    }

    private static void customersmenu() {
        System.out.println("\n   customers menu ");
        System.out.println("1- register new customer");
        System.out.println("2- show order history");
        System.out.println("3- back");
        System.out.print("enter choice: ");
        int ch2 = in.nextInt();

        switch (ch2) {
            case 1:
                System.out.println("registering new customer...");
                customers.registerCustomer();
                break;

            case 2:
                System.out.print("enter customer id: ");
                int cid = in.nextInt();
                System.out.println("loading order history...");
                customers.printOrderHistory(cid);
                break;

            case 3:
                System.out.println("returning to main menu...");
                break;

            default:
                System.out.println("invalid option.");
        }
    }

    private static void ordersmenu() {
        System.out.println("\n  orders menu ");
        System.out.println("1- search order by id");
        System.out.println("2- cancel order");
        System.out.println("3- update order status");
        System.out.println("4- orders between two dates");
        System.out.println("5- back");
        System.out.print("enter choice: ");
        int ch3 = in.nextInt();

        switch (ch3) {
            case 1:
                System.out.print("enter order id: ");
                int oid = in.nextInt();
                System.out.println("searching for order:");
                orders.searchOrderById(oid);
                break;

            case 2:
                System.out.print("enter order id: ");
                oid = in.nextInt();
                System.out.println("cancelling order:");
                orders.cancelOrder(oid);
                break;

            case 3:
                System.out.print("enter order id: ");
                oid = in.nextInt();
                System.out.print("enter new status: ");
                String status = in.next();
                System.out.println("updating order status:");
                orders.updateOrderStatus(oid, status);
                break;

            case 4:
                System.out.print("enter start date d/m/y: ");
                String d1 = in.next();
                System.out.print("enter end date d/m/y: ");
                String d2 = in.next();
                System.out.println("showing orders in range:");
                orders.ordersBetweenDates(d1, d2);
                break;

            case 5:
                System.out.println("returning to main menu:");
                break;

            default:
                System.out.println("invalid option.");
        }
    }

    private static void reviewsmenu() {
        System.out.println("\n reviews menu ");
        System.out.println("1- add review");
        System.out.println("2- edit review");
        System.out.println("3- show common reviewed products");
        System.out.println("4- back");
        System.out.print("enter choice: ");
        int ch4 = in.nextInt();

        switch (ch4) {
            case 1:
                System.out.println("adding new review...");
               
                System.out.print("product id: ");
                int pid = in.nextInt();
                System.out.print("customer id: ");
                int cid = in.nextInt();
               
                reviews.AddReview(pid, cid);
                break;

            case 2:
                System.out.println("editing existing review...");
                System.out.print("review id: ");
                int rid = in.nextInt();
                System.out.print("new rating (0 if no change): ");
                int rating = in.nextInt();
                in.nextLine();
                System.out.print("new comment (leave blank if no change): ");
                String comment = in.nextLine();
                reviews.updateReview(rid, rating, comment.isBlank() ? null : comment);
                break;

            case 3:
                System.out.print("first customer id: ");
                int c1 = in.nextInt();
                System.out.print("second customer id: ");
                int c2 = in.nextInt();
                System.out.println("finding common reviewed products...");
                reviews.commonReviewedProducts(c1, c2, products);
                break;

            case 4:
                System.out.println("returning to main menu...");
                break;

            default:
                System.out.println("invalid option.");
        }
    }
}
