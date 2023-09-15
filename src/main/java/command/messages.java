package command;

public class messages {
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
    public static void printGreetingMessage() {
        printLine();
        System.out.println("     Hello! I'm KenergeticBot");
        System.out.println("     What can I do for you?");
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }
}
