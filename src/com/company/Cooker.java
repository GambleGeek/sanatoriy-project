package com.company;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cooker {
    public static void main(String[] args) {
        cooker_login();
    }

    public static final String RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String PURPLE_BOLD = "\033[1;35m";

    public static void cooker_login() {
        Scanner in = new Scanner(System.in);
        int t = 0;
        while (true) {
            if (t >= 5) {
                System.out.println("You have exceeded the limit of attempts!");
                Main.menu();
                break;
            }
            System.out.println("Enter login : ");
            String login = in.nextLine();
            if (login.equals("cooker")) {
                t = 0;
                while (true) {
                    if (t >= 5) {
                        System.out.println("You have exceeded the limit of attempts!");
                        Main.menu();
                        break;
                    }
                    System.out.println("Enter password : ");
                    String password = in.nextLine();
                    if (password.equals("cooker123")) {
                        System.out.println("You are welcome!");
                        cooker();
                        t = 0;
                        break;
                    } else {
                        System.out.println("Password is not correct! Please try again.");
                        t++;
                    }
                }
                break;
            } else {
                System.out.println("Login is not correct! Please try again.");
                t++;
            }
        }
    }

    public static void cooker() {
        Scanner in = new Scanner(System.in);
        System.out.println("\n\tWelcome dear Cooker!" +
                "\nPlease enter numbers from the menu to work with program." +
                "\nIf you have finished, then enter : 0\n");

        System.out.println("\tCooker's Menu" +
                "\n1. Show menu" +
                "\n2. Meal's schedule" +
                "\n3. Client's menu" +
                "\n4. Order product" +
                "\n5. Schedule day" +
                "\n0. Exit");

        while (true) {
            System.out.println("\nEnter numbers from the menu : ");
            String assistants_menu = in.nextLine();
            if (assistants_menu.equals("1")) {
                System.out.println("\tMenu of meals");
                menuOfMeals();
            } else if (assistants_menu.equals("2")) {
                System.out.println("\tMeal's schedule");
                Meals_schedule();
            } else if (assistants_menu.equals("3")) {
                System.out.println("\tClient's menu");
                clients_menu();
            } else if (assistants_menu.equals("4")) {
                System.out.println("\tOrder product");
                order_product();
            } else if (assistants_menu.equals("5")) {
                System.out.println("\tSchedule day");
                schedule_day();
            } else if(assistants_menu.equals("0")){
                Main.menu();
                break;
            } else
                System.out.println("There is no option like this! Try again.");
        }
    }

    public static void menuOfMeals() {
        try {
            FileReader fReader = new FileReader("txt\\Menu all meal.txt");
            Scanner sc = new Scanner(fReader);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading file!");
            e.printStackTrace();
        }
    }

    public static void clients_menu() {
        try {
            FileReader fReader = new FileReader("txt\\CLIENT wish.txt");
            Scanner sc = new Scanner(fReader);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading file!");
            e.printStackTrace();
        }
    }

    public static void order_product() {
        Scanner in = new Scanner(System.in);
        String[] productArray = new String[10];
        int[] productPrice = new int[10];
        try {
            FileReader fReader = new FileReader("txt\\order product.txt");
            Scanner sc = new Scanner(fReader);

            FileReader fReader1 = new FileReader("txt\\products_price.txt");
            Scanner sc1 = new Scanner(fReader1);

            int c = 0;
            while (sc.hasNextLine() && sc1.hasNextLine()) {
                String data = sc.nextLine();
                int price = sc1.nextInt();
                System.out.println(data + " - " + price + " som");
                productPrice[c] = price;
                productArray[c] = data;
                c++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading file!");
            e.printStackTrace();
        }

        System.out.println("\nEnter name of the product : ");
        String name_product = in.nextLine();

        int index = 0;
        for(int i=0; i<productArray.length; i++){
            if(productArray[i].equals(name_product))
                index = i;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();

        if (Arrays.asList(productArray).contains(name_product)) {
            System.out.println("\n==============================" +
                    "\nCheck" +
                    "\nProduct's name : " + name_product +
                    "\nPrice : " + productPrice[index] + " som " +
                    "\nTime : " + formatter.format(date) +
                    "\n==============================");
        } else
            System.out.println("Product was not found!");

        try {
            File f1 = new File("txt\\history of orders.txt");
            FileWriter fileWriter = new FileWriter(f1.getName(),true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("\n==============================" +
                    "\nCheck" +
                    "\nProduct's name : " + name_product +
                    "\nPrice : " + productPrice[index] + " som " +
                    "\nTime : " + formatter.format(date) +
                    "\n==============================");
            bw.close();
            System.out.println("You have bought a procedure!");
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void Meals_schedule() {
        try {
            FileReader fReader = new FileReader("txt\\menu day.txt");
            Scanner sc = new Scanner(fReader);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading file!");
            e.printStackTrace();
        }
    }

    public static void schedule_day() {
        try {
            FileReader fReader = new FileReader("txt\\work day.txt");
            Scanner sc = new Scanner(fReader);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading file!");
            e.printStackTrace();
        }
    }
}


