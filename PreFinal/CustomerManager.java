import java.io.File;
import java.util.Scanner;
public class CustomerManager {
    
     private LinkedList<Customers> customers = new LinkedList<>();
     
    //reads customer data from a CSV file and stores them in the list
    public CustomerManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); 
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                customers.insert(new Customers(Integer.parseInt(data[0]), data[1], data[2]));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("Error "+ ex.getMessage());
        }
    }
    
    
    // Returns the list of customers
    public LinkedList<Customers> getCustomers() {
        return customers; }
   
    
    
    // Searches for a customer by ID
    public Customers searchCustomerById(int id) {
    if (customers.empty()) {
        System.out.println("No customers available.");
        return null;
    }
    customers.findFirst();
    while (!customers.last()) {
        if (customers.retrieve().getCustomerId() == id) {
            System.out.println("Customer found: " + customers.retrieve().getName());
            return customers.retrieve();
        }
        customers.findNext();
    }
    if (customers.retrieve().getCustomerId() == id) {
        System.out.println("Customer found: " + customers.retrieve().getName());
        return customers.retrieve();
    }
    System.out.println("Customer with ID " + id + " not found.");
    return null;
}

    // Checks if a customer ID already exists
    public boolean checkCustomerId(int cid) {
        return (searchCustomerById(cid) != null);
    }
    
    // Registers a new customer from user input
    public void registerCustomer() {
     Scanner sc = new Scanner(System.in);

     System.out.print("Customer ID: ");
     int id = sc.nextInt();
     sc.nextLine(); 

     System.out.print("Name: ");
     String name = sc.nextLine();

     System.out.print("Email: ");
     String email = sc.nextLine();

     Customers cust = new Customers();     
     cust.customerId = id;                
     cust.email = email;

        if(customers.empty()) {
          customers.insert(cust);
        } else {
            customers.findFirst();
            while (!customers.last()) {
                customers.findNext();
            }
          customers.insert(cust);
    }
    System.out.println("Customer registered.");
   }

    // Prints the order history for a specific customer
    public void printOrderHistory(int id) {
        Customers cust = searchCustomerById(id);
        if (cust == null) {
            System.out.println("Customer not found");
            return;
        }
        if (cust.getOrders().empty()) {
            System.out.println("No orders for " + cust.getName());
            return;
        }
        System.out.println("Order history for " + cust.getName() + ":");
        cust.getOrders().findFirst();
        while (!cust.getOrders().last()) {
            System.out.println(cust.getOrders().retrieve());
            cust.getOrders().findNext();
        }
        System.out.println(cust.getOrders().retrieve());
    }

}
