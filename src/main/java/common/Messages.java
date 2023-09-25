package common;

import listWhisper.task.ListOfTasks;
import listWhisper.task.Task;

public class Messages {
    public static void printAddMessage(ListOfTasks listOfTasks) {
        printStraightLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(listOfTasks.getTask(listOfTasks.getSize()) + "\n");
        System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.getSize()));
        printStraightLine();
    }

    public static void printDeleteMessage(ListOfTasks listOfTasks, Task task) {
        printStraightLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.getSize()));
        printStraightLine();
    }


    public static void printUnmarkMessage(Task task) {
        printStraightLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printStraightLine();
    }

    public static void printMarkMessage(Task task) {
        printStraightLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printStraightLine();
    }

    public static void printListMessage(ListOfTasks listOfTasks) {
        printStraightLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(listOfTasks.toString());
        printStraightLine();
    }

    public static void printByeMessage() {
        printStraightLine();
        System.out.println("Bye. Hope to see you again soon!");
        printStraightLine();
    }

    public static void printStraightLine() {
        System.out.println("-----------------------------------------------------");
    }
}
