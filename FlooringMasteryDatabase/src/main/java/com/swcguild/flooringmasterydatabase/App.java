package com.swcguild.flooringmasterydatabase;

import com.swcguild.flooringmasterydatabase.ui.ConsoleIO;
import com.swcguild.flooringmasterydatabase.ui.ConsoleUI;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConsoleUI ui;
        ConsoleIO con = new ConsoleIO();

        try {
            ui = new ConsoleUI();
        } catch (IOException ex) {
            con.toConsole("Product or Tax file error");
            System.exit(1);
            return;
        }

        boolean quit = true;

        //welcomoe screen
        ui.welcome();

        //save config file sets save to true or false depending on the config file
        //ui.setSave(ui.doWeSave());
        do {
            //display the main menu
            switch (ui.displayMain()) {
                case 1:
                    ui.displayOrders();
                    break;
                case 2:
                    ui.addOrder();
                    break;
                case 3:
                    ui.updateOrder();
                    break;
                case 4:
                    ui.removeOrder();
                    break;
                case 5:
                    quit = false;
                    break;

            }

        } while (quit);

    }
}
