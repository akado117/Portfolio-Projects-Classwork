/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterydatabase.ui;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ConsoleIO {

    Scanner sc = new Scanner(System.in);

    /**
     * prompts user with inputed prompt then returns an int the user enters
     *
     * @param prompt what to ask the user
     * @return integer the user inputs
     */
    public int promptInt(String prompt) {

        int input = 0;
        boolean tryagain;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(sc.nextLine());
                tryagain = false;
            } catch (NumberFormatException e) {
                tryagain = true;
                System.out.println("Please enter an integer");
            }

        } while (tryagain);

        return input;

    }

    /**
     *
     * @param prompt what to ask the user
     * @param min minimum value
     * @param max max value
     * @return
     */
    public int promptInt(String prompt, int min, int max) {
        int a;

        do {
            
            a = promptInt(prompt);

        } while (a < min || a > max);
        return a;
    }

    public String promptString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();

    }

    public float promptFloat(String prompt) {

        float input = 0;
        boolean tryagain;

        do {
            System.out.println(prompt);
            try {
                input = Float.parseFloat(sc.nextLine());
                tryagain = false;
            } catch (NumberFormatException e) {
                tryagain = true;
                System.out.println("Please enter a Float");
            }

        } while (tryagain);

        return input;
    }

    public float promptFloat(String prompt, float min, float max) {
        float a;

        do {
            
            a = promptFloat(prompt);

        } while (a < min || a > max);
        return a;
    }

    public double promptDouble(String prompt) {
        double input = 0;
        boolean tryagain;

        do {
            System.out.println(prompt);
            try {
                input = Double.parseDouble(sc.nextLine());
                tryagain = false;
            } catch (NumberFormatException e) {
                tryagain = true;
                System.out.println("Please enter a double");
            }

        } while (tryagain);

        return input;

    }

    public double promptDouble(String prompt, double min, double max) {
        double a;

        do {
            
            a =  promptDouble(prompt);

        } while (a < min || a > max);
        return a;
    }

    public void toConsole(String prompt) {
        System.out.println(prompt);
    }

}
