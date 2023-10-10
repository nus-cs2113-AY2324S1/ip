package AMY;

import java.util.Scanner;

public class Ui {
    public static final String BOT_NAME = "AMY";
    public static final String LINE = "____________________________________________________________";

    // Draw a line to separate commands after given inputs
    public static void drawLine() {
        System.out.println(LINE);
    }

    // Write a welcome message to welcome user when the program starts
    public static void welcomeMessage() {
        drawLine();
        String logo = "        \n"
                + "    /\\    |\\  /| \\   / \n"
                + "   /  \\   | \\/ |  \\ /  \n"
                + "  /----\\  |    |   |   \n"
                + " /      \\ |    |   |   \n";
        System.out.println("Hello from\n" + logo);

        drawLine();
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        drawLine();
    }

    // Write a bye message for the user when the program ends
    public static void byMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

}
