import java.util.Scanner;

public class Duke {
    public static final String BOT_NAME = "JS";
    public static final String LINE_DIVIDER = "----------------------------------------";
    public static void main(String[] args) {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        while(!(userInput.equals("bye"))) {
            System.out.println(LINE_DIVIDER);
            System.out.println(userInput);
            System.out.println(LINE_DIVIDER);
            userInput = input.nextLine();
        }
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}
