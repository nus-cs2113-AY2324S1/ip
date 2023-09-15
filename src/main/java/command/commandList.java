package command;

import task.Task;

import java.util.ArrayList;

import static command.messages.printLine;

public class commandList {
    public static void list(ArrayList<Task> taskList) {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("     %d.%s\n", i+1, taskList.get(i));
        }
        printLine();
    }

    public static void mark(ArrayList<Task> taskList, int listIndex) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        taskList.get(listIndex - 1).mark();
        System.out.printf("       %s\n", taskList.get(listIndex - 1));
        printLine();
    }

    public static void unmark(ArrayList<Task> taskList, int listIndex) {
        printLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        taskList.get(listIndex - 1).unmark();
        System.out.printf("       %s\n", taskList.get(listIndex - 1));
        printLine();
    }
}
