package com.company;

import java.util.Scanner;
import java.sql.*;
import java.io.*;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Assistant {

    public static Scanner in = new Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String YELLOW_BOLD = "\033[1;33m";

    public static void assistant_login(){
        int t = 0;
        while(true){
            if(t>=5) {
                System.out.println(RED_BOLD + "\nYou have exceeded the limit of attempts!" + RESET);
                Main.menu();
                break;
            }
            System.out.println("Enter login : ");
            String login = in.nextLine();
            if(login.equals("assistant123")){
                t = 0;
                while(true){
                    if(t>=5) {
                        System.out.println(RED_BOLD + "\nYou have exceeded the limit of attempts!" + RESET);
                        Main.menu();
                        break;
                    }
                    System.out.println("Enter password : ");
                    String password = in.nextLine();
                    if(password.equals("the_best")){
                        System.out.println(GREEN_BOLD + "You are welcome!" + RESET);
                        assistant();
                        t = 0;
                        break;
                    }
                    else {
                        System.out.println(RED_BOLD + "Password is not correct! Please try again." + RESET);
                        t++;
                    }
                }
                break;
            }
            else {
                System.out.println(RED_BOLD + "Login is not correct! Please try again." + RESET);
                t++;
            }
        }
    }

    public static void assistant(){
        System.out.println(GREEN_BOLD + "\n\tWelcome dear Assistant !" + RESET);
        System.out.println("Please enter numbers from the menu to work with program." +
                " If you have finished, then enter : 0");

        System.out.println(PURPLE_BOLD + "\n\tAssistant's Menu" + RESET);
        System.out.println("1. Show procedures list\n" +
                "2. Find client\n" +
                "3. Show all procedures\n" +
                "4. Show procedure's schedule\n" +
                "5. Buy procedure\n" +
                "6. Find procedure\n" +
                "0. Exit");
        connect();
        while(true){
            System.out.println(GREEN_BOLD + "\nEnter numbers from the menu : " + RESET);
            String assistants_menu = in.nextLine();
            if(assistants_menu.equals("1")){
                System.out.println(PURPLE_BOLD + "\tAll Procedures list" + RESET);
                show_procedureList();
            }
            else if(assistants_menu.equals("2")){
                System.out.println(PURPLE_BOLD + "\tFinding Client" + RESET);
                find_client();
            }
            else if(assistants_menu.equals("3")){
                System.out.println(PURPLE_BOLD + "\tAll procedures" + RESET);
                select_procedures();
            }
            else if(assistants_menu.equals("4")){
                System.out.println(PURPLE_BOLD + "\tProcedure's schedule\n" + RESET);
                procedures_schedule();
            }
            else if(assistants_menu.equals("5")){
                System.out.println(PURPLE_BOLD + "\tBuying procedure" + RESET);
                buy_procedures();
            }
            else if(assistants_menu.equals("6")){
                System.out.println(PURPLE_BOLD + "\tFinding procedure" + RESET);
                find_procedure();
            }
            else if(assistants_menu.equals("0")){
                Main.menu();
                break;
            }
            else
                System.out.println(RED_BOLD + "There is no option like this! Try again." + RESET);
        }
    }

    static Connection co;
    public static void connect(){
        try
        {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection (
                    "jdbc:sqlite:D:\\Рабочий Стол\\Sanatoriy\\sanatoriy.db");
            System.out.println(GREEN_BOLD + "Database connected" + RESET);

        }
        catch (Exception e)
        {
            System.out.println (e.getMessage());
        }
    }

    public static void select_procedures(){
        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT id, procedures, price, time " +
                            "From procedures ";
            ResultSet rs = statement.executeQuery(query);
            System.out.println("id                   procedure            price(som)           time");
            System.out.println("===                  ===========          =========            ==========");
            while(rs.next()){
                int id = rs.getInt("id");
                String procedures = rs.getString("procedures");
                int price = rs.getInt("price");
                String time = rs.getString("time");
                Object[] objects = {id, procedures, price, time};
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void procedures_schedule(){
        try{
            FileReader fReader = new FileReader("txt\\schedule.txt");
            Scanner sc = new Scanner(fReader);

            while(sc.hasNextLine()){
                String data = sc.nextLine();
                System.out.println(data);
            }
        }catch (FileNotFoundException e){
            System.out.println("File reading error");
            e.printStackTrace();
        }
    }

    public static void buy_procedures(){
        String [] ArrayProcedures = new String[8];
        int procedure_price = 0;

        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT id, procedures, price " +
                            "From procedures ";
            ResultSet rs = statement.executeQuery(query);
            System.out.println("id                   procedure            price(som)");
            System.out.println("===                  ===========          ========= ");
            int c = 0;
            while(rs.next()){
                int id = rs.getInt("id");
                String procedures = rs.getString("procedures");
                int price = rs.getInt("price");
                ArrayProcedures[c] = procedures;
                c++;
                Object[] objects = {id, procedures, price};
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Enter procedures name : ");
        String procedure_name = in.nextLine();

        if(Arrays.asList(ArrayProcedures).contains(procedure_name)){
            try{
                Statement statement = co.createStatement();
                String query =
                        "SELECT price " +
                                "From procedures " +
                                "\nWHERE procedures='" + procedure_name + "'";
                ResultSet rs = statement.executeQuery(query);
                procedure_price = rs.getInt("price");
                rs.close();
                statement.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date time = new Date();

            try {
                File f1 = new File("txt\\paid_procedures.txt");
                FileWriter fileWriter = new FileWriter(f1.getName(),true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write("\n\n================================" +
                        "\nCheck" +
                        "\nProcedure name : " + procedure_name +
                        "\nPrice : " + procedure_price +
                        "\nTime : " + formatter.format(time) +
                        "\n================================");
                bw.close();
                System.out.println(GREEN_BOLD + "You have bought a procedure!" + RESET);
            } catch(IOException e){
                e.printStackTrace();
            }

            System.out.println("================================" +
                    PURPLE_BOLD + "\nCheck" + RESET +
                    YELLOW_BOLD + "\nProcedure name : " + RESET + procedure_name +
                    YELLOW_BOLD + "\nPrice : " + RESET + procedure_price +
                    YELLOW_BOLD + "\nTime : " + RESET + formatter.format(time) +
                    "\n================================");
        }
        else
            System.out.println(RED_BOLD + "Procedure wasn't found!" + RESET);
    }

    public static void find_procedure(){
        String [] ArrayProcedures = new String[8];

        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT procedures " +
                            "From procedures ";
            ResultSet rs = statement.executeQuery(query);
            int c = 0;
            while(rs.next()){
                String procedures = rs.getString("procedures");
                ArrayProcedures[c] = procedures;
                c++;
            }
            rs.close();
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Enter name of the procedure : ");
        String procedure_name1 = in.nextLine();
        int clients_count = 0;

        if(Arrays.asList(ArrayProcedures).contains(procedure_name1)){
            System.out.println(YELLOW_BOLD + "\nProcedure name : " + RESET + procedure_name1);
            try{
                Statement statement = co.createStatement();
                String query =
                        "SELECT price " +
                                "From procedures " +
                                "\nWHERE procedures='" + procedure_name1 + "'";
                ResultSet rs = statement.executeQuery(query);
                int price = rs.getInt("price");
                System.out.println(YELLOW_BOLD + "Price : " + RESET + price + "\n");
                rs.close();
                statement.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            try{
                Statement statement = co.createStatement();
                String query =
                        "SELECT name, procedure " +
                                "From clients " +
                                "\nWHERE procedure LIKE " + "'%" + procedure_name1 + "%'";
                ResultSet rs = statement.executeQuery(query);
                System.out.println("clients              procedure");
                System.out.println("===========          ===================================");
                while(rs.next()){
                    String name = rs.getString("name");
                    String procedure = rs.getString("procedure");
                    Object[] objects = {name, procedure};
                    for (Object o:objects){
                        System.out.print(o + " ");
                        if (o.toString().length() < 20){
                            for (int k = 0; k < (20 - o.toString().length()); k++){
                                System.out.print("" + " ");
                            }
                        }
                    }
                    clients_count++;
                    System.out.println();
                }
                rs.close();
                statement.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println(YELLOW_BOLD + "\nNumber of clients : " + RESET + clients_count);
        }
        else
            System.out.println(RED_BOLD + "Procedure was not found!" + RESET);
    }

    public static void find_client(){
        String [] ArrayClients = new String[10];

        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT name " +
                            "From clients ";
            ResultSet rs = statement.executeQuery(query);
            int c = 0;
            while(rs.next()){
                String clients = rs.getString("name");
                ArrayClients[c] = clients;
                c++;
            }
            rs.close();
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Enter name of the client : ");
        String client_name = in.nextLine();

        if(Arrays.asList(ArrayClients).contains(client_name)){
            System.out.println(YELLOW_BOLD + "Client's name : " + RESET + client_name + "\n");
            try{
                Statement statement = co.createStatement();
                String query =
                        "SELECT name, illness, procedure, visits " +
                                "From clients " +
                                "\nWHERE name LIKE " + "'%" + client_name + "%'";
                ResultSet rs = statement.executeQuery(query);
                System.out.println("client               illness              procedures                 visits");
                System.out.println("==============       =========            ========================   ======");
                while(rs.next()){
                    String name = rs.getString("name");
                    String illness = rs.getString("illness");
                    String procedure = rs.getString("procedure");
                    int visits = rs.getInt("visits");
                    Object[] objects = {name, illness, procedure, visits};
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
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else
            System.out.println(RED_BOLD + "Client was not found!" + RESET);
    }

    public static void show_procedureList(){
        String[] AllProceduresArray = new String[8];
        try{
            Statement statement = co.createStatement();
            String query =
                    "SELECT procedures " +
                            "From procedures ";
            ResultSet rs = statement.executeQuery(query);
            int c = 0;
            while(rs.next()){
                String procedures = rs.getString("procedures");
                AllProceduresArray[c] = procedures;
                c++;
            }
            rs.close();
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        for(int j=0; j<AllProceduresArray.length; j++){
            System.out.println(YELLOW_BOLD + "\nProcedure's name : " + RESET + AllProceduresArray[j]);
            try{
                Statement statement = co.createStatement();
                String query =
                        "SELECT name " +
                                "From clients " +
                                "\nWHERE procedure LIKE " + "'%" + AllProceduresArray[j] + "%'";
                ResultSet rs = statement.executeQuery(query);
                System.out.println("Clients");
                System.out.println("======================");
                while(rs.next()){
                    String name = rs.getString("name");
                    System.out.println(name);
                }
                System.out.println("======================");
                rs.close();
                statement.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
