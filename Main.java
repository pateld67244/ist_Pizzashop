package edu.psu.ist;
/*
Project: Lab 9
Purpose Details: Pizza ordering application
Course: IST 242
Author: Joe Oakes, Dhaval Patel
Date Developed: 06/16/20
Last Date Changed: 06/17/20
Rev: 2
 */


import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class Main {
    int cCount = 1;
    static int oCount = 1;
    static int tCount = 1;


    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char HELP_CODE = '?';

        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();  // Creating tlist array from Transaction class (Array)

        Order order1 = new Order(oCount);
        Transaction trans1 = new Transaction(tCount, order1, PaymentType.cash);

        Menu menu1 = new Menu(1, "Plain", 7.99);  // menu array
        Menu menu2 = new Menu(2, "Meat & peperoni", 11.99);
        Menu menu3 = new Menu(3, "Extra Cheese", 10.99);
        Menu menu4 = new Menu(4, "Veggie", 13.0);

        mList.add(menu1); // adding menu 1-4 into menulist
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);

        oList.add(order1); // order will be added to orderList
        tList.add(trans1); // adding transaction type to order list



        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch (userAction) {
                case CUST_CODE:
                    cList.add(main.addCustomer());
                    break;
                case CUST_PRNT:
                    Customer.printCustomer(cList);
                    break;
                case MENU_CODE:
                    Menu.listMenu(mList);
                    break;
                case ORDE_CODE:
                    main.addNewOrder(mList, tList, cList);
                    break;
                case TRAN_CODE:
                    Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    //method used to get an action for the menu
    public static char getAction(String prompt) {
        Scanner scnr = new Scanner(System.in);
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    //method used to add new customer into customer list
    public Customer addCustomer() {
        Customer cust = new Customer(cCount++);
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        System.out.println("New customer added!");
        return cust;
    }


    //method used to add new order
    public void addNewOrder(ArrayList<Menu> mList, ArrayList<Transaction> tList, ArrayList<Customer> cList) {
        Order order = new Order(++oCount);
        Menu.listMenu(mList);
        //  Transaction.listTransactions(tList);
        Scanner scnr = new Scanner(System.in);
        int cust = -1;     // Commenting start from here >>>
        boolean userFound = false;    // Explain Commenting
        while (!userFound) {
            System.out.println("Enter a customer ID: ");
            cust = scnr.nextInt();
            for (int i = 0; i < cList.size(); i++) {
                if (cust == cList.get(i).getCustomerId()) {
                    userFound = true;
                }
            }

        }


        System.out.println("Select item for order: ");
        int itemID = scnr.nextInt();
        order.addItem(mList.get(itemID - 1)); // saving order in orders array
        ArrayList<Menu> m = order.getItems();   // getting order items from order saved array
        //   ArrayList<Transaction> t = Transaction.listTransactions();

        // = order.addItem(order);
        //   itemID = mList.size();
        int q = order.getorderId();
        System.out.println("Please enter quantity: ");
        int quanty = scnr.nextInt();


        System.out.println("New order is created for customer ID: " + cust);


        System.out.println("The menu id is: " + m.get(0).getmenuId()); //
        System.out.println("Your order is: " + m.get(0).getmenuItem());
        System.out.println("The price of 1 order is: " + m.get(0).getmenuPrice());
        double price = m.get(0).getmenuPrice() * quanty;  // total order calc
        System.out.println("Total price: " + price); // printing total price to the consolse

        // payment type
        System.out.print("\nPlease select the payment type: " +
                "\n1. Cash" +
                "\n2. Credit \n");
        int T_type = scnr.nextInt();
        PaymentType ptype = PaymentType.credit;
        System.out.println("Thank you for your business");
        if(T_type == 1){
            ptype = PaymentType.cash;
        } ;


    }
}
