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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class OrderManager {
     private LinkedList<Orders> orders = new LinkedList<>();

    
     
     public OrderManager(String fileName) {
        // reads it in one line so split is needed between (,)
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); // skip header of table
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int oid = Integer.parseInt(data[0]);
                int cid = Integer.parseInt(data[1]);
                // added we have to check the run //
                
                   String  pp =  data[2].replaceAll("\"", "");
                    String [] p = pp.split(";");
                    Integer [] pids = new Integer [p.length];
                    for ( int i = 0 ; i<pids.length ; i++)
                            pids[i] = Integer.parseInt(p[i].trim());
                
           
                ///////////////////////////
                
                double price = Double.parseDouble(data[3]);
                String date = data[4];
                String status = data[5]; // we have 6
                ////////////////////////////////
                
                 Orders order = new Orders(oid, cid, pids, price, date, status ); //pids is added
                orders.insert(order);
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LinkedList<Orders> getOrders() { return orders; } // returns link not copy 

    public Orders searchOrderById(int id) {
        if (orders.empty()) return null;
        
        orders.findFirst();
        while (!orders.last()) {
            if (orders.retrieve().getOrderId() == id) return orders.retrieve();
            orders.findNext();
        }
        if (orders.retrieve().getOrderId() == id) return orders.retrieve();
        return null;
    }

    public boolean cancelOrder(int id) {
        Orders o = searchOrderById(id);
        if (o == null) return false;
        if (o.getStatus().equalsIgnoreCase("cancelled")) return false;
        o.setStatus("cancelled");
        return true; // do we have to insert the order we canceled to list?
    }

    public boolean updateOrderStatus(int id, String newStatus) { ///?
        Orders o = searchOrderById(id);
        if (o == null) return false;
        if (o.getStatus().equalsIgnoreCase("cancelled")) return false;
        o.setStatus(newStatus);
        return true;
    }

    // Orders between two dates
    public LinkedList<Orders> ordersBetweenDates(String d1, String d2) {
        LinkedList<Orders> result = new LinkedList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate start = LocalDate.parse(d1, fmt);
        LocalDate end = LocalDate.parse(d2, fmt);

        if (orders.empty()) return result;
        orders.findFirst();
        while (!orders.last()) {
            if (orders.retrieve().getDate().isAfter(start) && orders.retrieve().getDate().isBefore(end))
                result.insert(orders.retrieve());
            orders.findNext();
        }
        if (orders.retrieve().getDate().isAfter(start) && orders.retrieve().getDate().isBefore(end))
            result.insert(orders.retrieve());
        return result;
    }
      public boolean checkOrderID(int oid) /// added if found or not maybe used in main
    {
        if (!orders.empty())
        {
            orders.findFirst();
            while (!orders.last())
            {
                if (orders.retrieve().getOrderId()== oid)
                    return true;
                orders.findNext();
            }
            if (orders.retrieve().getOrderId()== oid)
                return true;
        }
        return false;
    }
}
