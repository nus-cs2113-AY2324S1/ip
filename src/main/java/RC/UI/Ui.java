package RC.UI;

import java.util.Scanner;

public class Ui {
    private static final String MESSAGE_WELCOME = "\tHello! I'm RC\n\tWhat can I do for you?\n";
    private static final String MESSAGE_EXIT = "\tBye. Hope to see you again soon!\n";

    public Ui() {
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void welcomeMessage() {
        showMessage(MESSAGE_WELCOME);
    }

    public void exitMessage() {
        showMessage(MESSAGE_EXIT);
    }

    public String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }
}
