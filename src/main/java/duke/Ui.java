package duke;

import java.util.Scanner;

public class Ui {

    public static String getUserInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        return line;
    }

    public void showLoadingError() {
        System.out.println("Can't display the list due to unknown errors");
    }

    public void greetToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm TUM");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void byeToUsers() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

