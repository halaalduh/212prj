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
     private DoubleLinkedList<Orders> orders = new DoubleLinkedList<>();

    public OrderManager(String fileName) {
        try {
            File docsfile = new File(fileName);
            Scanner reader = new Scanner(docsfile);
            reader.nextLine(); // skip header
            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int oid = Integer.parseInt(data[0]);
                int cid = Integer.parseInt(data[1]);
                double price = Double.parseDouble(data[3]);
                String date = data[4];
                String status = data[5];
                Orders order = new Orders(oid, cid, price, date, status);
                orders.insert(order);
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DoubleLinkedList<Orders> getOrders() { return orders; }

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
        return true;
    }

    public boolean updateOrderStatus(int id, String newStatus) {
        Orders o = searchOrderById(id);
        if (o == null) return false;
        if (o.getStatus().equalsIgnoreCase("cancelled")) return false;
        o.setStatus(newStatus);
        return true;
    }

    // Orders between two dates
    public DoubleLinkedList<Orders> ordersBetweenDates(String d1, String d2) {
        DoubleLinkedList<Orders> result = new DoubleLinkedList<>();
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
}
