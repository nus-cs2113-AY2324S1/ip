package userinputs;

import java.util.Scanner;

public class Ui {
    public static final String DELETE_TASK_COMMAND = "delete ";
    public static final String MARK_TASK_COMMAND = "mark ";
    public static final String UNMARK_TASK_COMMAND = "unmark ";
    public static final String LIST_TASK_COMMAND = "list";
    public static final String EXIT_BOT_COMMAND = "bye";
    public static final String TODO_TASK_COMMAND = "todo ";
    public static final String DEADLINE_TASK_COMMAND = "deadline ";
    public static final String DEADLINE_DATE_COMMAND = "/by ";
    public static final String EVENT_TASK_COMMAND = "event ";
    public static final String EVENT_TASK_START = "/from ";
    public static final String EVENT_TASK_END = "/to ";
    public static final String USER_HELP_COMMAND = "help";

    private final Scanner scanner;
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________ ";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.printf((outputFormat) + "%n",
                "hello! I'm Zran, your personal assistant:)! \n" +
                        "    Type in your to dos for the day and press enter! \n " +
                        "   Type 'help' to view to list of commands the bot accepts! ");
    }

    public String readCommand() {
        System.out.print("Enter a command: ");
        return scanner.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("Goodbye <3 have a great day ahead!");
    }

    public void closeScanner() {
        scanner.close();
    }

}
