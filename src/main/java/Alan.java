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

    public static void main(String[] args) {
        String[] taskList = new String[100];
        int currentTaskListIndex = 0;

        printGreet();

        String userInput = " ";
        Scanner in = new Scanner(System.in);

        while(!userInput.equals("bye")) {
            System.out.print("Input: ");
            userInput = in.nextLine();
            printHorizontalLine();

            if (userInput.equals("list")) {
                for (int i = 0; i < currentTaskListIndex; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
            } else {
                taskList[currentTaskListIndex] = userInput;
                currentTaskListIndex++;
                System.out.println("added: " + userInput);
            }
            printHorizontalLine();
        }

        printExit();
    }
}
