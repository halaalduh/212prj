
package proj;
import java.time.LocalDate;


public class Orders {

    int orderId;
    int customerReference;
    LinkedList<Integer> products = new LinkedList<Integer>();  
    double total_price;
    LocalDate date;
    String status; // (pending, shipped, delivered, cancelled)

    // default constructor
    public Orders() {
        this.orderId = 0;
        this.customerReference = 0;
        this.total_price = 0;
        this.status = "";
        this.date = LocalDate.now();
    }

    // constructor used when reading from file
    public Orders(int orderId, int customerReference, Integer[] pids, double total_price, String date, String status) {
        this.orderId = orderId;
        this.customerReference = customerReference;
        this.total_price = total_price;
        this.date = LocalDate.parse(date); // convert date string to LocalDate
        this.status = status;
        
        // add all product IDs to the list
        for (int i = 0; i < pids.length; i++) // this is new added
            products.insert(pids[i]); // store each product ID in the order
    }

    // getters
    public int getOrderId() {
        return orderId;
    }

    public int getCustomerReference() {
        return customerReference;
    }

    public LinkedList<Integer> getProducts() {
        return products;
    }

    public double getTotal_price() {
        return total_price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    // setters
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerReference(int customerReference) {
        this.customerReference = customerReference;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // add a product ID to the list
    public void addProduct(Integer product) {
        products.insert(product);
    }

    // remove a product ID from the list
    public boolean removeProduct(Integer pid) {
        if (!products.empty()) {
            products.findFirst();
            while (!products.last()) {
                if (products.retrieve().equals(pid)) {
                    products.remove();
                    return true;
                }
                products.findNext();
            }
            if (products.retrieve().equals(pid)) {
                products.remove();
                return true;
            }
        }
        return false;
    }

    // show all order details as text
    @Override
    public String toString() {
        String str = "Orders{" + "orderId=" + orderId + ", customerReference=" + customerReference
                + ", total price=" + total_price
                + ", status=" + status
                + ", date=" + date;

        if (!products.empty()) {
            str += " (products List: ";
            products.findFirst();
            while (!products.last()) {
                str += products.retrieve() + " "; // name?
                products.findNext();
            }
            str += products.retrieve() + ")";
        }

        str += " }";
        return str;
    }
}




