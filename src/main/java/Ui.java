import java.util.Scanner;

public abstract class Ui {
    public static void welcomeMessage() {
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
    }
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
