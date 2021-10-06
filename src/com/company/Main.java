package com.company;

import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        menu();
    }

    public static Scanner in = new Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String PURPLE_BOLD = "\033[1;35m";

    public static void menu(){
        System.out.println(PURPLE_BOLD + "\t Main menu" + RESET);
        System.out.println(GREEN_BOLD + "\tWelcome dear User!" + RESET);
        System.out.println("If you want to start program, enter : 1" +
                "\nIf you want to quit, enter : 0" +
                "\nFAQ : 2");

        while(true){
            System.out.println(GREEN_BOLD + "\nEnter numbers from the menu : " + RESET);
            String menu = in.nextLine();
            if(menu.equals("1")){
                System.out.println(GREEN_BOLD + "Program starts!" + RESET);
                choosing_account();
                break;
            }
            else if(menu.equals("2")){
                System.out.println(PURPLE_BOLD + "\t\t\tFAQ" + RESET);
                try{
                    FileReader fReader = new FileReader("text\\info.txt");
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
            else if(menu.equals("0")){
                System.out.println(PURPLE_BOLD + "=======================END=======================" + RESET);
                break;
            }
            else
                System.out.println(RED_BOLD + "There is no option like this! Try again." + RESET);
        }
    }

    public static void choosing_account(){
        System.out.println("To start the program, please enter account type and key word : ");

        while(true){
            String account_type = in.nextLine();
            if(account_type.equalsIgnoreCase("assistant")){
                System.out.println(PURPLE_BOLD + "\tAssistant's account" + RESET);
                Assistant.assistant_login();
                break;
            }
            else if(account_type.equalsIgnoreCase("patient")){
                System.out.println(PURPLE_BOLD + "\tPatient's account" + RESET);
                Patient.client_login();
                break;
            }
            else if(account_type.equalsIgnoreCase("manager")){
                System.out.println(PURPLE_BOLD + "\tManager's account" + RESET);
                Manager.manager_login();
                break;
            }
            else if(account_type.equalsIgnoreCase("director")){
                System.out.println(PURPLE_BOLD + "\tDirector's account" + RESET);
                Director.director_login();
                break;
            }
            else if(account_type.equalsIgnoreCase("cooker")){
                System.out.println(PURPLE_BOLD + "\tCooker's account" + RESET);
                Cooker.cooker_login();
                break;
            }
            else
                System.out.println(RED_BOLD + "Sorry, but there is no account like this! Try again." + RESET);
        }
    }
}
