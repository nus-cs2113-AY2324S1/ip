import java.util.Scanner;

public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printWelcomeMessage() {
        printKEN();
        System.out.println("  Hello! I'm Ken");
        System.out.println("  What would you like to do?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye for now!");
    }

    public static void printLine() {
        System.out.println(" ____________________________________________________________");
    }
    private static void printKEN() {
        System.out.println("  K   K  EEEEE  N   N");
        System.out.println("  K  K   E      NN  N");
        System.out.println("  KKK    EEEE   N N N");
        System.out.println("  K  K   E      N  NN");
        System.out.println("  K   K  EEEEE  N   N");
    }

}
