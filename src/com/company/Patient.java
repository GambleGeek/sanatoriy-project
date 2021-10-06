package com.company;
import java.sql.*;
import java.util.Scanner;
public class Patient {

    public static void main(String[] args) {
        client_login();
    }

    public static Connection co;
    public static final String RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String PURPLE_BOLD = "\033[1;35m";

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:c:\\sqlite\\sanatoriy.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void client_login() {
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
            if (login.equals("Patient")) {
                t = 0;
                while (true) {
                    if (t >= 5) {
                        System.out.println("You have exceeded the limit of attempts!");
                        Main.menu();
                        break;
                    }
                    System.out.println("Enter password : ");
                    String password = in.nextLine();
                    if (password.equals("Password")) {
                        System.out.println("You are welcome!");
                        client();
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

    public static void client() {
        Scanner in = new Scanner(System.in);
        System.out.println("\n\t" +
                "Welcome dear Patient!" +
                " \nPlease enter numbers from the menu to work with program." +
                " If you have finished, then enter : 0\n");

        System.out.println("\tPatient's Menu" +
                "\n1. Show the history of visits to procedures" +
                "\n2. Show the last date of attendance of procedures" +
                "\n3. Show payment history to procedures" +
                "\n4. Show the schedule for procedures" +
                "\n5. Show my info" +
                "\n6. Show days of stay at the Sanatorium of all patients" +
                "\n0. Exit");

        while (true) {
            System.out.println(GREEN_BOLD + "\nEnter numbers from the menu : " + RESET);
            String managers_menu = in.nextLine();
            if (managers_menu.equals("1")) {
                System.out.println("\nThe history of visits to procedures \n");
                System.out.println("================                       =============                       ==============================             ============");
                System.out.println("     name                            date of registrtion                       date of procedures                    days for expire");
                System.out.println("================                       =============                       ==============================             ============");
                connect();
                schedule();
            } else if (managers_menu.equals("2")) {
                System.out.println("\tShow the last date of attendance of procedures");
                System.out.println("================               =============       ");
                System.out.println("     name                   date of procedure    ");
                System.out.println("================               =============       ");
                connect();
                dateofproc();
            } else if (managers_menu.equals("3")) {
                System.out.println("\tShow payment history to procedures");
                connect();
                buycheck();
            } else if (managers_menu.equals("4")) {
                System.out.println("\tShow the schedule for procedures");
                System.out.println("====                          =============                 =================== ");
                System.out.println(" id                          name of procedure                     time          ");
                System.out.println("====                          =============e                =================== ");
                connect();
                procedures();
            } else if (managers_menu.equals("5")) {
                System.out.println("\tShow my info");
                System.out.println("==========           ===========        ======             ============         ==========          ==========             =======");
                System.out.println("  name              date of birth       gender               height               weight             bloodtype              rezus");
                System.out.println("==========           ===========        ======             ============         ==========          ==========             =======");
                connect();
                info();

            } else if (managers_menu.equals("6")) {
                System.out.println("\tShow my day of stay at the Sanatorium");
                System.out.println("================                       =============                       ==============================             ============");
                System.out.println("     name                            date of registrtion                       date of procedures                    days for expire");
                System.out.println("================                       =============                       ==============================             ============");
                connect();
                schedule1();
            } else if(managers_menu.equals("0")){
                Main.menu();
                break;
            } else
                System.out.println("There is no option like this! Try again.");
        }
    }
    //1
    public static void schedule() {
        try {
            Statement statement1 = co.createStatement();
            String query = "SELECT * FROM schedule WHERE ID = 1";
            ResultSet rs1 = statement1.executeQuery(query);
            while (rs1.next()) {
                String name = rs1.getString("name");
                String dateofreg = rs1.getString("date_of _registration");
                String dateofprod = rs1.getString("date_of_procedure");
                String dateofexpire = rs1.getString("days_for_expire");
                Object[] objects = {name, dateofreg, dateofprod, dateofexpire};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 40) {
                        for (int k = 0; k < (40 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
            rs1.close();
            statement1.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //2
    public static void dateofproc() {
        try {
            Statement statement1 = co.createStatement();
            String query = "SELECT * FROM schedule WHERE ID = 1";
            ResultSet rs1 = statement1.executeQuery(query);
            while (rs1.next()) {
                String name = rs1.getString("name");
                String dateofprod = rs1.getString("date_of_procedure");
                Object[] objects = {name, dateofprod};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 30) {
                        for (int k = 0; k < (30 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
            rs1.close();
            statement1.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    //3
    public static void buycheck() {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("\t\nPrice\n");
            Statement statement1 = co.createStatement();
            System.out.println("Write the name of the Patient: ");
            String name = in.nextLine();
            System.out.println("\n\tWrite name of the procedure: \n");
            String procedures_name1 = in.nextLine();
            String qstring = "INSERT INTO my_table (name, procedure)\n" +
                    "VALUES ('" + name + "'" + "," + "'"  + procedures_name1 + "')";
            statement1.executeUpdate(qstring);

            String knlstring = "SELECT * FROM my_table";
            ResultSet rs1 = statement1.executeQuery(knlstring);
            while (rs1.next()) {
                String name1 = rs1.getString("name");
                String procedure = rs1.getString("procedure");
                Object[] objects = {name1, procedure};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 50) {
                        for (int k = 0; k < (50 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
            rs1.close();
            statement1.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //4
    public static void procedures() {
        try {
            Statement statement1 = co.createStatement();
            String query = "SELECT * FROM procedures";
            ResultSet rs1 = statement1.executeQuery(query);
            while (rs1.next()) {
                int id = rs1.getInt("id");
                String procedures = rs1.getString("procedures");
                String time = rs1.getString("time");
                Object[] objects = {id, procedures, time};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 30) {
                        for (int k = 0; k < (30 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
            rs1.close();
            statement1.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //5
    public static void info() {
        try {
            Statement statement1 = co.createStatement();
            String query = "SELECT * FROM info WHERE ID = 1";
            ResultSet rs1 = statement1.executeQuery(query);
            while (rs1.next()) {
                String name = rs1.getString("name");
                String brth = rs1.getString("birthdate");
                String gender = rs1.getString("gender");
                String height = rs1.getString("height");
                String weight = rs1.getString("weight");
                String bloodtype = rs1.getString("bloodtype");
                String rezus = rs1.getString("rezus");
                Object[] objects = {name, brth, gender, height, weight, bloodtype, rezus};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 20) {
                        for (int k = 0; k < (20 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
            rs1.close();
            statement1.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //6
    public static void schedule1() {
        try {
            Statement statement1 = co.createStatement();
            String query = "SELECT * FROM schedule";
            ResultSet rs1 = statement1.executeQuery(query);
            while (rs1.next()) {
                String name = rs1.getString("name");
                String dateofreg = rs1.getString("date_of _registration");
                String dateofprod = rs1.getString("date_of_procedure");
                String dateofexpire = rs1.getString("days_for_expire");
                Object[] objects = {name, dateofreg, dateofprod, dateofexpire};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 40) {
                        for (int k = 0; k < (40 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println();
            }
            rs1.close();
            statement1.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}


