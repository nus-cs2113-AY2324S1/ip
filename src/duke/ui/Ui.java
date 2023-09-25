package duke.ui;

import java.util.Scanner;

public class Ui {

    private final Scanner in;

    private static final String LINE = "____________________________________________________________";

    private static final String BOB = "\t   ___    ___  ___ \n"
            + "\t  / __\\  /___\\/ __\\\n"
            + "\t /__\\// //  //__\\/\\\n"
            + "\t/ \\/  \\/ \\_// \\/  \\\n"
            + "\t\\_____/\\___/\\_____/\n";

    public Ui() {
        in = new Scanner(System.in);
    }

    public void println(String line) {
        System.out.println("\t" + line);
    }

    public void printLine() {
        println(LINE);
    }

    public void printWelcome() {
        printLine();
        println("Hello! I'm\n" + BOB);
        println("What can I do for you?");
        printLine();
    }

    public void printFarewell() {
        println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void printCommandResult(String result) {
        println(result);
    }

    public void printLoadingError() {
        println("Unable to find file. Defaulting to empty list...");
    }

    public String getCommand() {
        return in.nextLine();
    }
}
