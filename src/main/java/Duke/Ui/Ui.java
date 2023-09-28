package Duke.Ui;

import java.util.Scanner;

public class Ui {
    private static final String LINE_DASHES = "____________________________________________________________";
    private final String START_MESSAGE = "Hello! I'm Chatty\n" + "What can I do for you?";
    private final Scanner in;
    private final String END_MESSAGE = "Bye. Hope to see you again soon!";
    public Ui(){
        in = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        printMessage(START_MESSAGE);
    }

    public void printByeMessage() {
        printMessage(END_MESSAGE);
    }

    public void printLine() {
        System.out.println(LINE_DASHES);
    }
    public void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }
}
