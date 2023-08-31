import java.util.Scanner;

public class Alan {

    public static void printGreet() {
        printHorizontalLine();
        String man = " @/\n" +
                     "/| \n" +
                     "/ \\";

        System.out.println(man);
        System.out.println("Hello! I'm Alan");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void echoUserInput() {
        String userLine;
        Scanner userInput = new Scanner(System.in);

        System.out.print("Input: ");
        userLine = userInput.nextLine();

        printHorizontalLine();
        if (userLine.equals("bye")) {
            printExit();
        } else {
            System.out.println(userLine);
            printHorizontalLine();
            echoUserInput();
        }

    }

    public static void main(String[] args) {
        printGreet();
        echoUserInput();
    }
}
