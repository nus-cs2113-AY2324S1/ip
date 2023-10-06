package Chatty;

import java.util.Scanner;

public class Ui {
    public static final String LINE = "____________________________________________________________";
    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Chatty!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("Error loading or creating data file.");
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}