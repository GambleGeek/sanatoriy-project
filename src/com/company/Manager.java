package com.company;
import java.sql.*;
import java.util.*;

public class Manager {

    public static void main(String[] args) {
        manager_login();
    }
    public static final String RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String PURPLE_BOLD = "\033[1;35m";

    public static void manager_login() {
        Scanner in = new Scanner(System.in);
        int t = 0;
        while (true) {
            if (t >= 5) {
                System.out.println(RED_BOLD + "You have exceeded the limit of attempts!" + RESET);
                Main.menu();
                break;
            }
            System.out.println("Enter login : ");
            String login = in.nextLine();


            if (login.equals("Manager")) {
                t = 0;
                while (true) {
                    if (t >= 5) {
                        System.out.println(RED_BOLD + "You have exceeded the limit of attempts!" + RESET);
                        Main.menu();
                        break;
                    }
                    System.out.println("Enter password : ");
                    String password = in.nextLine();
                    if (password.equals("Password")) {
                        System.out.println(GREEN_BOLD + "You are welcome!" + RESET);
                        manager();
                        t = 0;
                        break;
                    } else {
                        System.out.println(RED_BOLD + "Password is not correct! Please try again." + RESET);
                        t++;
                    }
                }
                break;
            } else {
                System.out.println(RED_BOLD + "Login is not correct! Please try again." + RESET);
                t++;
            }
        }
    }

    public static void manager() {
        Scanner in = new Scanner(System.in);
        System.out.println("\n\t" +
                GREEN_BOLD + "Welcome dear Manager!" + RESET +
                GREEN_BOLD + " \nPlease enter numbers from the menu to work with program."  + RESET +
                PURPLE_BOLD + " If you have finished, then enter : 0\n"  + RESET);

        System.out.println(GREEN_BOLD + "\tManager's Menu" + RESET +
                "\n1. Show clients list" +
                "\n2. Show amount of clients" +
                "\n3. Find client" +
                "\n4. Change price of the procedure" +
                "\n5. Change time or name of the procedure" +
                "\n6. Show client with maximum number of visits" +
                "\n7. Show client with minimum number of visits" +
                "\n0. Exit");

        while (true) {
            System.out.println("\nEnter numbers from the menu : ");
            String managers_menu = in.nextLine();
            if (managers_menu.equals("1")) {
                System.out.println("\t                                                                          Client's list");
                System.out.println("================                                  =============                                       ====================================          ============");
                System.out.println("     name                                           diagnose                                                      procedures                           visits");
                System.out.println("================                                  =============                                       ====================================          ============");
                connect();
                show_clients();
            } else if (managers_menu.equals("2")) {
                System.out.println("\t                                      Show amount of clients");
                System.out.println("================                                  =============                                       ====================================          ============");
                System.out.println("     name                                           diagnose                                                      procedures                           visits");
                System.out.println("================                                  =============                                       ====================================          ============");
                connect();
                show_amount_of_clients();
            } else if (managers_menu.equals("3")) {
                System.out.println("\t                                      Find client");

                connect();
                find_client();
            } else if (managers_menu.equals("4")) {
                System.out.println("\t                                      Change price of the procedure");
                connect();
                change_price();
            } else if (managers_menu.equals("5")) {
                System.out.println("\t                                      Change time or name of the procedure");
                connect();
                change_time_name_p();
            } else if (managers_menu.equals("6")) {
                System.out.println("\t                                      Show client with maximum number of visits");
                connect();
                max_visits();
            } else if (managers_menu.equals("7")) {
                System.out.println("\t                                      Show client with minimum number of visits");
                connect();
                min_visits();
            } else if(managers_menu.equals("0")){
                Main.menu();
                break;
            } else
                System.out.println("There is no option like this! Try again.");
        }
    }

    static Connection co;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:c:\\sqlite\\sanatoriy.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void show_clients() {
        try {
            Statement statement1 = co.createStatement();
            String query =
                    "SELECT id, name, illness, procedure, visits " +
                            " FROM clients " +
                            "ORDER BY id";
            ResultSet rs1 = statement1.executeQuery(query);
            while (rs1.next()) {
                int id = rs1.getInt("id");
                String name = rs1.getString("name");
                String illness = rs1.getString("illness");
                String procedure = rs1.getString("procedure");
                String visits = rs1.getString("visits");
                print_clients_info();
                break;
            }
            rs1.close();
            statement1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void show_amount_of_clients() {
        try {
            Statement statement1 = co.createStatement();
            String qstring =
                    "SELECT COUNT(*) as visits FROM clients";
            ResultSet rs1 = statement1.executeQuery(qstring);
            while (rs1.next()) {

                int visits = rs1.getInt("visits");
                System.out.print("Current number of clients is: " + visits + "\n");
                print_clients_info();
            }
            rs1.close();
            statement1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static void find_client() {
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to choose?\n" +
                "1. Client diagnose\n" +
                "2. Client's info card\n" +
                "3. Client's history\n");

        String change_menu = in.nextLine();
        while (true) {
            try {
                if (change_menu.equals("1")) {
                    System.out.println("\t\nClient\n");
                    Statement statement1 = co.createStatement();
                    System.out.println("\n\tEnter clients name: \n");
                    String client_name = in.nextLine();

                    String qstring = "SELECT * FROM clients " +
                            "WHERE name='" + client_name + "'";
                    ResultSet rs1 = statement1.executeQuery(qstring);
                    while (rs1.next()) {
                        String name = rs1.getString("name");
                        String illness = rs1.getString("illness");
                        Object[] objects = {name, illness};
                        System.out.println("================                                  =============");
                        System.out.println("     name                                            diagnose   ");
                        System.out.println("================                                  =============");

                        for (Object o : objects) {
                            System.out.print(o + " ");
                            if (o.toString().length() < 51) {
                                for (int k = 0; k < (51 - o.toString().length()); k++) {
                                    System.out.print(" ");
                                }
                            }
                        }
                    }
                    statement1.close();
                    rs1.close();
                    break;

                } else if (change_menu.equals("2")) {
                    Statement statement1 = co.createStatement();
                    System.out.println("Enter clients name: ");
                    String client_name = in.nextLine();
                    String mstring = "SELECT * FROM info  WHERE name = '" + client_name + "'";
                    ResultSet rs1 = statement1.executeQuery(mstring);
                    while (rs1.next()) {
                        int id = rs1.getInt("id");
                        String name = rs1.getString("name");
                        String gender = rs1.getString("gender");
                        String birthdate = rs1.getString("birthdate");
                        String height = rs1.getString("height");
                        String weight = rs1.getString("weight");
                        String bloodtype = rs1.getString("bloodtype");
                        Object[] objects = {name, gender, birthdate, height, weight, bloodtype};
                        System.out.println("================       =============                 ===================           ============               ============                  ============");
                        System.out.println("     name                 Gender                       Date of Birth                 Height                      Weight                      Blood Type");
                        System.out.println("================       =============                 ===================           ============               ============                  ============");
                        for (Object o : objects) {
                            System.out.print(o + " ");
                            if (o.toString().length() < 28) {
                                for (int k = 0; k < (28 - o.toString().length()); k++) {
                                    System.out.print(" ");
                                }
                            }
                        }
                    }
                    rs1.close();
                    statement1.close();
                    break;
                } else if (change_menu.equals("3")) {
                    System.out.println("\t\nClient\n");
                    Statement statement1 = co.createStatement();
                    System.out.println("\n\tEnter clients name: \n");
                    String client_name = in.nextLine();
                    String qstring = "SELECT * FROM clients WHERE name = '" + client_name + "'";
                    ResultSet rs1 = statement1.executeQuery(qstring);
                    while (rs1.next()) {
                        int id = rs1.getInt("id");
                        String name = rs1.getString("name");
                        String illness = rs1.getString("illness");
                        Object[] objects = {name, illness};
                        System.out.println("================              =============");
                        System.out.println("     name                       diagnose   ");
                        System.out.println("================              =============");
                        for (Object o : objects) {
                            System.out.print(o + " ");
                            if (o.toString().length() < 30) {
                                for (int k = 0; k < (30 - o.toString().length()); k++) {
                                    System.out.print(" ");
                                }
                            }
                        }
                    }
                    statement1.close();
                    rs1.close();
                    break;


                } else
                    System.out.println("There is no option like this! Try again.");
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

        }
    }

    public static void change_price() {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\t\nPrice\n");
                Statement statement1 = co.createStatement();
                System.out.println("\n\tWrite name of the procedure: \n");
                String procedures_name1 = in.nextLine();
                System.out.println("Write the Price you need: ");
                String price = in.nextLine();
                String qstring = "UPDATE procedures SET price = '" + price + "'" +
                        " WHERE procedures = '" + procedures_name1 + "'";
                statement1.executeUpdate(qstring);

                String knlstring = "SELECT id, procedures, time " +
                        " FROM procedures";
                ResultSet rs1 = statement1.executeQuery(knlstring);
                while (rs1.next()) {
                    System.out.println("================          =============                     =============");
                    System.out.println("   procedure                 price                               time");
                    System.out.println("================          =============                     ==============");
                    int id = rs1.getInt("id");
                    String procedures = rs1.getString("procedures");
                    String time1 = rs1.getString("time");
                    print_procedure_info();

                    statement1.close();
                    rs1.close();
                    System.out.println("");
                }
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public static void change_time_name_p() {

        Scanner in = new Scanner(System.in);
        System.out.println("What do you want to change?\n" +
                "1. Time\n" +
                "2. Name of the procedure\n");

        String change_menu = in.nextLine();
        while (true) {
            try {
                if (change_menu.equals("1")) {
                    System.out.println("\t\nTime\n");
                    Statement statement1 = co.createStatement();
                    System.out.println("\n\tWrite name of the procedure: \n");
                    String procedures_name = in.nextLine();
                    System.out.println("Write the time you need: ");
                    String time = in.nextLine();
                    String qstring = "UPDATE procedures SET time = '" + time + "'" +
                            " WHERE procedures = '" + procedures_name + "'";
                    System.out.println("================          =============                     =============");
                    System.out.println("   procedure                 price                               time");
                    System.out.println("================          =============                     ==============");

                    statement1.executeUpdate(qstring);
                    print_procedure_info();
                    System.out.println("");

                    String knlstring = "SELECT id, procedures, time " +
                            " FROM procedures ";

                    ResultSet rs1 = statement1.executeQuery(knlstring);
                    while (rs1.next()) {
                        int id = rs1.getInt("id");
                        String procedures = rs1.getString("procedures");
                        String time1 = rs1.getString("time");

                    }
                    statement1.close();
                    rs1.close();
                    break;

                } else if (change_menu.equals("2")) {
                    System.out.println("\tName of the procedure");
                    Statement statement1 = co.createStatement();
                    System.out.println("\n\tWrite name of the procedure: ");
                    String procedures_name = in.nextLine();
                    System.out.println("Write the new name you need: ");
                    String new_name = in.nextLine();
                    String kstring = "UPDATE procedures " +
                            "set procedures = '" + new_name + "'" +
                            " WHERE procedures = '" + procedures_name + "'";

                    System.out.println("================          =============                     =============");
                    System.out.println("   procedure                 price                               time");
                    System.out.println("================          =============                     ==============");

                    statement1.executeUpdate(kstring);
                    print_procedure_info();
                    System.out.println("");

                    String knstring = "SELECT id, procedures, time " +
                            " FROM procedures ";
                    ResultSet rs1 = statement1.executeQuery(knstring);
                    while (rs1.next()) {
                        int id = rs1.getInt("id");
                        String procedures = rs1.getString("procedures");
                        String time = rs1.getString("time");
                    }
                    statement1.close();
                    rs1.close();
                    break;

                } else
                    System.out.println("There is no option like this! Try again.");
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    public static void max_visits() {

        try {
            Statement statement1 = co.createStatement();
            String vstring =
                    "SELECT name, visits\n" +
                            "FROM clients\n" +
                            "WHERE visits = (SELECT MAX(visits) FROM clients);";
            ResultSet rs1 = statement1.executeQuery(vstring);
            while (rs1.next()) {
                String name = rs1.getString("name");
                int visits = rs1.getInt("visits");
                Object[] objects = {name, visits};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 20) {
                        for (int k = 0; k < (20 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
            }
            rs1.close();
            statement1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void min_visits() {
        try {
            Statement statement1 = co.createStatement();
            String vstring =
                    "SELECT name, visits\n" +
                            "FROM clients\n" +
                            "WHERE visits = (SELECT MIN(visits) FROM clients);";
            ResultSet rs1 = statement1.executeQuery(vstring);
            while (rs1.next()) {
                String name = rs1.getString("name");
                int visits = rs1.getInt("visits");
                Object[] objects = {name, visits};
                for (Object o : objects) {
                    System.out.print(o + " ");
                    if (o.toString().length() < 20) {
                        for (int k = 0; k < (20 - o.toString().length()); k++) {
                            System.out.print(" ");
                        }
                    }
                }
            }
            rs1.close();
            statement1.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Prints
    public static void print_clients_info() {
        try {
            Statement statement = co.createStatement();
            String query =
                    "SELECT * " +
                            "From 'clients'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                String illness = rs.getString("illness");
                String procedure = rs.getString("procedure");
                int visits = rs.getInt("visits");
                Object[] objects = {name, illness, procedure, visits};
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
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void print_procedure_info() {
        try {
            Statement statement = co.createStatement();
            String query =
                    "SELECT procedures, price, time " +
                            "FROM procedures";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String procedures = rs.getString("procedures");
                int price = rs.getInt("price");
                String time = rs.getString("time");
                Object[] objects = {procedures, price, time};
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
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

