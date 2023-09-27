package kenergeticbot.ui;

import kenergeticbot.TaskList;
import kenergeticbot.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {

    public static String SEPARATING_LINE = "    ____________________________________________________________";
    private final Scanner in;
    private final PrintStream out;
    public static void printLine() {
        System.out.println(SEPARATING_LINE);
    }

    public TextUi() {
        this(System.in, System.out);
    }
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
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

    public static void printAddedTaskMessage(TaskList taskList, Task newTask) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.getSize());
    }

    public static void printDeleteTaskMessage (TaskList taskList) {

    }
}
