package common;

import listWhisper.task.TaskList;
import listWhisper.task.Task;

public class Messages {
    public static final String MESSAGE_SHOW_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    public static final String MESSAGE_MARK_AS_NOT_DONE = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_MARKED_AS_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_TASKS_IN_YOUR_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_ADDED_THIS_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_REMOVE_THIS_TASK = "Noted. I've removed this task:";


    public static void printAddMessage(TaskList taskList) {
        printStraightLine();
        System.out.println(MESSAGE_ADDED_THIS_TASK);
        System.out.println(taskList.getTask(taskList.getSize() - 1) + "\n");
        System.out.println(String.format(MESSAGE_SHOW_NUMBER_OF_TASKS, taskList.getSize()));
        printStraightLine();
    }

    public static void printDeleteMessage(TaskList taskList, Task task) {
        printStraightLine();
        System.out.println(MESSAGE_REMOVE_THIS_TASK);
        System.out.println(task);
        System.out.println(String.format(MESSAGE_SHOW_NUMBER_OF_TASKS, taskList.getSize()));
        printStraightLine();
    }


    public static void printUnmarkMessage(Task task) {
        printStraightLine();
        System.out.println(MESSAGE_MARK_AS_NOT_DONE);
        System.out.println(task);
        printStraightLine();
    }

    public static void printMarkMessage(Task task) {
        printStraightLine();
        System.out.println(MESSAGE_MARKED_AS_DONE);
        System.out.println(task);
        printStraightLine();
    }

    public static void printListMessage(TaskList taskList) {
        printStraightLine();
        System.out.println(MESSAGE_TASKS_IN_YOUR_LIST);
        System.out.println(taskList.toString());
        printStraightLine();
    }

    public static void printByeMessage() {
        printStraightLine();
        System.out.println(MESSAGE_BYE);
        printStraightLine();
    }

    public static void printGreetingMessage() {
        Messages.printStraightLine();
        System.out.println("Hello! I'm ListWhisper");
        System.out.println("What can I do for you?");
        Messages.printStraightLine();
    }

    public static void printStraightLine() {
        System.out.println("-----------------------------------------------------");
    }
}
