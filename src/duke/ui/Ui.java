package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
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

    private void println(String line) {
        System.out.println("\t" + line);
    }

    public void printWelcome() {
        println(LINE);
        println("Hello! I'm\n" + BOB);
        println("What can I do for you?");
        println(LINE);
    }

    public void printFarewell() {
        println("Bye. Hope to see you again soon!");
        println(LINE);
    }

    public void printList(ArrayList<Task> taskItems) {
        for (int i = 0; i < taskItems.size(); i++) {
            println(String.format("%d. %s", i+1, taskItems.get(i).getTask()));
        }
    }

    public String getCommand() {
        return in.nextLine();
    }
}
