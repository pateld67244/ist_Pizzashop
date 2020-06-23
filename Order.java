package edu.psu.ist;

import java.util.ArrayList;

public class Order {
    //Class Level Variables - Protect the data
    private int orderId;
    private Customer cust;
    private ArrayList<Menu> menuItem; //accessing menu class for menu items

    //Constructor Method
    public Order(int _orderId) {
        this.orderId = _orderId;
        menuItem = new ArrayList<>(); // created a new blank arraylist for menuItems
    }

    //Setters and Getters
    public int getorderId() { return orderId; }
    public void setorderId(int _orderId) {
        this.orderId = _orderId;
    }

    public ArrayList<Menu> getItems() {    //getting menuItems from Menu class
        return menuItem;
    }

    //adding a menu item into the order
    public void addItem(Menu menu) {

        menuItem.add(menu);  // this will add menuItems in the
    }

    public static void printOrders(ArrayList<Order> oList) {  // printing menu ID and Menu Item


        for (Order order : oList) { // saving order to the order list
            System.out.println("Menu ID: " + order.getorderId());
            System.out.println("Menu Item: " + order.menuItem);

        }

    }
}
