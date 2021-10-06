package com.company;
import java.util.Scanner;
import java.sql.*;

public class Director {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE_BOLD = "\033[1;34m";
    public static final String ANSI_BLACK_BOLD = "\033[1;30m";
    public static void main(String[] args) {
        director_login();
    }
    public static void director_login() {
        Scanner in = new Scanner(System.in);
        int t = 0;
        while (true) {
            if (t >= 5) {
                System.out.println(ANSI_RED + "You have exceeded the limit of attempts!" + ANSI_RESET + ANSI_BLACK_BOLD);
                break;
            }
            System.out.println(ANSI_BLACK_BOLD + "Enter login : ");
            String login = in.nextLine();
            if (login.equals("director")) {
                t = 0;
                while (true) {
                    if (t >= 5) {
                        System.out.println(ANSI_RED + "You have exceeded the limit of attempts!" + ANSI_RESET);
                        Main.menu();
                        break;
                    }
                    System.out.println("Enter password : ");
                    String password = in.nextLine();
                    if (password.equals("directorrr")) {
                        director();
                        t = 0;
                        break;
                    } else {
                        System.out.println(ANSI_RED + "Password is not correct! Please try again." + ANSI_RESET);
                        Main.menu();
                        t++;
                    }
                }
                break;
            } else {
                System.out.println(ANSI_RED + "Login is not correct! Please try again." + ANSI_RESET);
                t++;
            }
        }
    }
    public static void director() {
        Scanner in = new Scanner(System.in);
        System.out.println(ANSI_BLACK_BOLD + "\n\t\tWelcome dear Director !\n" + ANSI_RESET +
                "\nPlease enter numbers from the menu to work with program.\n" +
                "\nIf you have finished, then enter : 0 " );

        System.out.println(ANSI_BLUE_BOLD + "\t\tDirector's Menu"
                + ANSI_RESET +"\n1. Show the list of procedures." +
                "\n2. Show the list of clients." +
                "\n3. Show the list of personell." +
                "\n4. Delete or add a procedure." +
                "\n5. Delete or add a worker." +
                "\n0. Exit.");
        while (true) {
            System.out.println(ANSI_BLACK_BOLD + "\nEnter numbers from the menu : " + ANSI_RESET);
            String directors_menu = in.nextLine();
            if (directors_menu.equals("1")) {
                System.out.println(ANSI_BLUE_BOLD + "\tThe list of procedures:"  + ANSI_RESET);
                connect_procedurelists();
                select1();
            } else if (directors_menu.equals("2")) {
                System.out.println(ANSI_BLUE_BOLD + "\tThe list of clients:" + ANSI_RESET);
                connect_procedurelists();
                select2();
            } else if (directors_menu.equals("3")) {
                System.out.println(ANSI_BLUE_BOLD + "\tThe list of personell:" + ANSI_RESET);
                connect_procedurelists();
                select3();

            } else if (directors_menu.equals("4")) {
                System.out.println(ANSI_BLUE_BOLD + "\tPlease, print '1' to delete, '2' to add a procedure, any other to quit:" + ANSI_RESET);
                connect_procedurelists();
                select4();

            } else if (directors_menu.equals("5")) {
                System.out.println(ANSI_BLUE_BOLD + "\tPlease, print '1' to delete, '2' to add a worker, any other to quit:" + ANSI_RESET);
                connect_procedurelists();
                select5();
            } else if(directors_menu.equals("0")){
                Main.menu();
                break;
            } else
                System.out.println(ANSI_RED + "There is no option like this, please try again." + ANSI_RESET);
        }
    }
    static Connection co;

    public static void connect_procedurelists() {

        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\sanatoriy.db");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void select1() {
        try {
            Statement statement = co.createStatement();
            String query;
            query = "SELECT id, procedures, price " +
                    "FROM procedures";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String procedures = rs.getString("procedures");
                String price = rs.getString("price");
                Object[] objects = {procedures, price};
                for (Object o:objects){
                    System.out.print(o + " ");
                    if (o.toString().length() < 20){
                        for (int k = 0; k < (20 - o.toString().length()); k++){
                            System.out.print("" + " ");
                        }
                    }
                }
                System.out.println();

            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  //the list of procedures

    public static void select2() {
        try {
            Statement statement = co.createStatement();
            String query = "SELECT id, name, illness " +
                    "FROM clients ";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String illness = rs.getString("illness");
                Object[] objects = {name, illness};
                for (Object o:objects){
                    System.out.print(o + " ");
                    if (o.toString().length() < 20){
                        for (int k = 0; k < (20 - o.toString().length()); k++){
                            System.out.print("" + " ");
                        }
                    }
                }
                System.out.println();

            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }  // the list of clients

    public static void select3() {
        try {
            Statement statement = co.createStatement();
            String query = "SELECT id, name, profession " +
                    "FROM personell ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String profession = rs.getString("profession");
                Object[] objects = {name, profession};
                for (Object o:objects){
                    System.out.print(o + " ");
                    if (o.toString().length() < 20){
                        for (int k = 0; k < (20 - o.toString().length()); k++){
                            System.out.print("" + " ");
                        }
                    }
                }
                System.out.println();

            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }  // the list of personell

    public static void select4() {
        Scanner in = new Scanner(System.in);
        int procedurenumber = in.nextInt();
        if (procedurenumber == 1) {

            System.out.println(ANSI_BLACK_BOLD + "Please, print the name of a procedure you want to delete:" + ANSI_RESET);
            try {


                Statement statement = co.createStatement();
                String deleter = in.nextLine();
                String deleter1 = in.nextLine();

                String query = "DELETE FROM procedures WHERE procedures = '" + deleter1 + "';";
                PreparedStatement pst = co.prepareStatement(query);
                System.out.println(ANSI_GREEN + " DELETED" + ANSI_RESET);
                pst.executeUpdate();
                statement.close();



            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (procedurenumber == 2) {

            try {
                System.out.println(ANSI_BLACK_BOLD + "Please, print the name of a procedure you want to add:" + ANSI_RESET);
                String name = in.nextLine();
                String namer = in.nextLine();
                System.out.println(ANSI_BLACK_BOLD + "Please, print the price  of a procedure you want to add:" + ANSI_RESET);
                String pricer = in.nextLine();
                System.out.println(ANSI_BLACK_BOLD + "Please, print the schedule of a procedure you want to add:" + ANSI_RESET);
                String scheduler = in.nextLine();

                Statement statement = co.createStatement();
                String query = "INSERT INTO procedures (procedures, price, time) " +
                        "VALUES ('" + namer + "','" + pricer + "','" + scheduler + "')";
                PreparedStatement pst = co.prepareStatement(query);
                System.out.println(ANSI_GREEN + "ADDED" + ANSI_RESET);
                pst.executeUpdate();
                statement.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else{
            System.out.println(ANSI_GREEN + "BACK TO MENU" + ANSI_RESET);
        }
    }  // deleting or adding a procedure

    public static void select5() {
        Scanner in = new Scanner(System.in);
        int procedurenumber = in.nextInt();
        if (procedurenumber == 1) {

            System.out.println(ANSI_BLACK_BOLD + "Please, print the name of a worker you want to delete:" + ANSI_RESET);
            try {
                String deleter = in.nextLine();
                String deleter1 = in.nextLine();

                Statement statement = co.createStatement();
                String query = "DELETE FROM personell WHERE name = '" + deleter1 + "';";
                PreparedStatement pst = co.prepareStatement(query);
                System.out.println(ANSI_GREEN + "DELETED" + ANSI_RESET);
                pst.executeUpdate();
                statement.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (procedurenumber == 2) {

            try {
                System.out.println(ANSI_BLACK_BOLD + "Please, print the name of a worker you want to add:" + ANSI_RESET);
                String name = in.nextLine();
                String namer = in.nextLine();
                System.out.println(ANSI_BLACK_BOLD + "Please, print the profession of a worker you want to add:" + ANSI_RESET);
                String professiorer = in.nextLine();


                Statement statement = co.createStatement();
                String query = "INSERT INTO personell (name, profession) " +
                        "VALUES ('" + namer + "','" + professiorer + "')";
                PreparedStatement pst = co.prepareStatement(query);
                System.out.println(ANSI_GREEN + "ADDED" + ANSI_RESET);
                pst.executeUpdate();
                statement.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else{
            System.out.println("BACK TO MENU");
        }
    }  // deleting or adding a worker
}
