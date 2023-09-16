package kenergeticbot.command;

import kenergeticbot.task.Task;

import java.util.ArrayList;

public class CommonMessages {

    public static String SEPARATING_LINE = "    ____________________________________________________________";
    public static void printLine() {
        System.out.println(SEPARATING_LINE);
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

    public static void printAddedTaskMessage(ArrayList<Task> taskList, Task newTask) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.printf("     Now you have %d tasks in the list.\n", taskList.size());
    }
}
