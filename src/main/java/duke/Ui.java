package duke;

import java.util.Scanner;

public class Ui {

    /**
     * This method is designed to get the user input
     * @return user input
     */
    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        return line;
    }

    /**
     * if error occurs, then print this error message
     */
    public void showLoadingError() {
        System.out.println("Can't display the list due to unknown errors");
    }

    /**
     * This is a greeting from our program to user who uses it
     */
    public void greetToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUM");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * When the user decides to exit, then say farewell to him
     */
    public void byeToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

