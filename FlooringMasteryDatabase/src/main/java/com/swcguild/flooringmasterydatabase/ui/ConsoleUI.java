/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterydatabase.ui;

import com.swcguild.flooringmasterydatabase.Dao.OrderDAO;
import com.swcguild.flooringmasterydatabase.Dao.ProductDAO;
import com.swcguild.flooringmasterydatabase.Dao.TaxesDAO;
import com.swcguild.flooringmasterydatabase.logic.Logic;
import com.swcguild.flooringmasterydatabase.model.Order;
import com.swcguild.flooringmasterydatabase.model.UserInput;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class ConsoleUI {

    ConsoleIO con = new ConsoleIO();
    OrderDAO ord = new OrderDAO();
    TaxesDAO tax = new TaxesDAO();
    ProductDAO prod = new ProductDAO();
    Logic logic;
    String date = "";
    Boolean save;//is saving enabled

    public ConsoleUI() throws IOException {
        this.logic = new Logic();

        //initialize all the daos using the beans
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        tax = (TaxesDAO) ctx.getBean("TaxesDAO");
        ord = (OrderDAO) ctx.getBean("OrderDAO");
        prod = (ProductDAO) ctx.getBean("ProductDAO");

    }

    public void setSave(boolean save) {
        this.save = save;
    }

    

    public void removeOrder() {
        
      
        Order order = null;
        int orderNum;

        //checks to make sure the order exists, if not enter an order to delete
        do {
            orderNum = con.promptInt("Please enter the order number you would like to remove");
            order = ord.getOrder(orderNum);
            //if the order number equals zero they are returned to the main menu
            if (orderNum == 0) {
                return;
            }

            if (order == null) {
                con.toConsole("Order not found, please enter another order number or 0 to go back to the main menu");
            }
        } while (order == null);

        displayOrder(order);

        String reallyDelete = con.promptString("Really delete? (y/n)");
        if (reallyDelete.equals("y")) {
            ord.deleteOrder(orderNum);
        }

    }

    public void addOrder() {

        UserInput usr = new UserInput();
        
        

       

        usr.setDate(getDate());

        usr.setCustomerName(con.promptString("Please enter customers name"));
        usr.setArea(con.promptInt("Please enter an area", 0, 100000));

        //makes sure the user chooses a product that exists
        usr.setProductType(productChooser(false));
        
        //makes sure the user enters a tax that exists
        usr.setState(taxChooser(false));

        //runs the user through the logic layer to take care of all calculations
        Order order = logic.calculate(usr);//runs the calculations method

        
        //sets the order to the order number it will be assigned within the database
        order.setOrderNumber(ord.highestOrderNumber()+1);
        
        //displays the order number
        displayOrder(order);

        //asks the user if they would like to keep the order
        String saveOrderToMemory = con.promptString("Keep the above order? (y/n)");

        //adds order to the hashmap that is in memory
        if (saveOrderToMemory.equals("y")) {
            ord.addOrder(order);
        }

    }

// 
    public void updateOrder() {

//              
     
        Order order;
        int ordNum;
        boolean doesExist = false;

        do {//make sure the order exists, keep asking until they enter one that does exist
            ordNum = con.promptInt("Please enter the order number you would like to edit");
            order = ord.getOrder(ordNum);
            if (order != null) {
                doesExist = true;
            } else {
                con.toConsole("Please enter an order number that exists");
                doesExist = false;
            }

        } while (!doesExist);

        //check to see if they would like to change the customers name
        String name = con.promptString("Change customer name? (" + order.getCustomerName() + ") ");
        if (!name.equals("")) {
            order.setCustomerName(name);
        }

        boolean isFloat = false;
        do {
            String area = con.promptString("Change area (" + order.getArea() + ")");
            try {
                if (area.equals("")) {
                    break;
                }
                order.setArea(Float.parseFloat(area));
                isFloat = true;

            } catch (NumberFormatException ex) {
                con.toConsole("Must be a number");
            }

        } while (!isFloat);

        //prompts the user to enter a product type
        String userChoice = productChooser(true);
        if (!userChoice.equals("")) {//if a blank space is entered it doesn't change the variable
            order.setProductType(userChoice);
        }

        userChoice = taxChooser(true);
        if (!userChoice.equals("")) {//if a blank space is entered it doesn't change the variable
            order.setState(userChoice);
        }

        //send order to the calculator to update those that need update
        try {
            order = logic.calculate(order);
        } catch (IOException ex) {
            con.toConsole("Error: " + ex);
        }

        order.setOrderNumber(ordNum);
        ord.updateOrder(order);
        
        con.toConsole("Order has been updated to the following");
        displayOrder(order);
        
        
        

    }

   

    //used to display orders for a certain date
    public void displayOrders() {

        String date = getDate();

        Order[] orders = ord.listOrders(date);

        for (Order order : orders) {
            displayOrder(order);
        }

    }
    
     //used to have the user enter a valid date
    private String getDate() {
        con.toConsole("Please enter the date of the order (MMDDYYYY)");
        String month;
        String day;
        String year;

        do {
            month = con.promptString("Enter a month MM") + "";
            if (month.length() < 2) {
                con.toConsole("Enter in the MM format");
            }
        } while (month.length() < 2);

        do {
            day = con.promptString("Enter a day DD") + "";
            if (day.length() < 2) {
                con.toConsole("Enter in the DD format");
            }
        } while (day.length() < 2);

        do {
            year = con.promptString("Enter a year YYYY") + "";
            if (year.length() < 4) {
                con.toConsole("Enter in the YYYY format");
            }
        } while (year.length() < 4);

        return month + day + year;
    }

//   
    public String productChooser(boolean spaceEscape) {
        

        con.toConsole("Please enter a product type, choose from the following");
        String[] products = prod.listProductTypes();//sets all product types to a String[] of products
        for (String product : products) {
            con.toConsole(product);
        }

        String userChoice;
        do {
            userChoice = con.promptString("");
            for (String product : products) {
                if (product.equals(userChoice)) {
                    return userChoice;

                }
                if (spaceEscape && userChoice.equals("")) {
                    return "";
                }
            }
            con.toConsole("Please chose something from the list above");

        } while (true);
    }

    public String taxChooser(boolean spaceEscape) {

        con.toConsole("Please enter a state, choose from the following");
        String[] taxes = tax.getTaxStates();//gets all the available taxes
        for (String tax : taxes) {
            con.toConsole(tax);
        }
        //used to check to make sure the user put in a valid state
        String userChoice;
        do {
            userChoice = con.promptString("");
            for (String tax : taxes) {
                if (tax.equals(userChoice)) {
                    return userChoice;

                }
                if (spaceEscape && userChoice.equals("")) {
                    return "";
                }
            }

            con.toConsole("Please chose something from the list above");

        } while (true);
    }

    //main menu displayer
    public int displayMain() {
        return con.promptInt("***************Main Menu******************\n**\n"
                + "Enter number corresponding to intended choice\n"
                + "1-Display orders for desired date\n"
                + "2-Add order\n"
                + "3-Edit an order\n"
                + "4-Remove an order\n"
                + "5-Quit", 1, 5);

    }

    //simple welcome screen
    public void welcome() {
        con.toConsole("Wecome to the Amazing Floor Store Tracker System");
    }

    //displays the order when given an order
    public void displayOrder(Order ord) {
        con.toConsole("************$$$$$$$$$$$************\n"
                + "The order number is: " + ord.getOrderNumber() + "\t"
                + "The order date is: " + ord.getDate() + " \n"
                + "Customer name: " + ord.getCustomerName() + "\n"
                + "The material cost is " + ord.getMaterialCost() + "\n"
                + "The labor cost is " + ord.getLaborCost() + "\n"
                + "The tax is: " + ord.getTax() + "\n"
                + "The Total is: " + ord.getTotal() + "\n"
                + "************$$$$$$$$$$$************\n");
    }

 //    public void openNewFile(String newDate) {
//        if (!date.equals("")) {
//            int choice = con.promptInt("ATTEMPT TO ACCESS NEW FILE \n"
//                    + "would you like to save old data; 1-yes 2-no", 1, 2);
//            if (choice == 1) {
//                save();
//            } else {
//                con.toConsole("data in memory has been cleared");
//            }
//        }
//        //if a new date aka a new file is being accessed clear the orders hashmap in preparation for new orders.
//        if (!date.equals(newDate)) {
//            ord.clearMap();
//        }
//
//    }
    //    public void loadFile() {
//
//        do {
//            String newDate = con.promptString("Please enter the date of the file you would like to load");
//
//            openNewFile(newDate);//checks to make sure they want to save before loading a new file
//
//            ord.setOrderDate(newDate);
//            if (ord.doesFileExist()) {
//                try {
//                    ord.loadOrders();
//
//                } catch (IOException ex) {
//                    con.toConsole("Error: " + ex);
//                }
//                return;
//            } else {
//                con.toConsole("Please enter a valid date");
//
//            }
//        } while (true);
//
//    }
    //    //calls a read from the config file, sets a ui variable to true or false
//    //depending on if we can save or not
//    public boolean doWeSave() {
//        SaveDetermination s = new SaveDetermination();
//        boolean save = false;
//        try {
//            save = s.loadSaveConfig();
//        } catch (IOException ex) {
//            con.toConsole("Error: " + ex);
//        }
//        return save;
//    }
//       public void save() {
//        if (save) {
//            try {
//                ord.saveOrdersToFile();
//            } catch (FileNotFoundException ex) {
//                con.toConsole("Error: " + ex);
//            }
//
//        }
//
//    }
}
