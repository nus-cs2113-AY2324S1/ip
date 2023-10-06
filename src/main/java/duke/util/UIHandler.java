package main.java.duke.util;

import main.java.duke.util.task.Task;

public class UIHandler {
    public static void endMessage() {
        // program exit statement
        System.out.println(" Bye. Hope to see you again soon! :D");
        partition();
        System.out.println("                          -END-                             ");
    }

    public static void continueMessage() {
        partition();
        // prompt user for input and store it
        System.out.print("What do you want to do next? :o\n" + ">>");
    }

    public static void printInvalidCommandMessage() {
        System.out.println("Invalid command! :/");
    }

    public static void printTaskDeleted(int selectedItem, Task removedTask) {
        System.out.println("Task " + selectedItem + " delete!");
        System.out.println(removedTask);
    }

    public static void printTaskAddedMessage(Task task, TaskList taskList) {
        System.out.println("I've added this task to your list:");
        System.out.println(task);
        System.out.println("You now have " + taskList.size() + " task(s) in your list. :]");
    }

    public static void printUnmarkedMessage(int selectedItem, TaskList taskList) {
        System.out.println("Task " + selectedItem + " unmarked!\n" +
                taskList.get(selectedItem - 1));
    }

    public static void integerErrorMessage() {
        System.out.println("Invalid integer input! :(");
    }

    public static void printMarkedMessage(int selectedItem, TaskList taskList) {
        System.out.println("Task " + selectedItem + " marked!\n" +
                taskList.get(selectedItem - 1));
    }

    public static void printTasks(TaskList taskList) {
        System.out.println("Here are the item(s) in your list. :)");
        // print out tasks and number each task
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    public static void startMessage() {
        partition();
        // name of the robot in ASCII art
        String name = " _____ _                       _     _       _         \n" +
                "/  __ \\ |                     | |   | |     | |        \n" +
                "| /  \\/ |__   ___  ___ _______| |__ | | ___ | | __ ____\n" +
                "| |   | '_ \\ / _ \\/ _ \\_  / _ \\ '_ \\| |/ _ \\| |/ /|_  /\n" +
                "| \\__/\\ | | |  __/  __// /  __/ |_) | | (_) |   <  / / \n" +
                " \\____/_| |_|\\___|\\___/___\\___|_.__/|_|\\___/|_|\\_\\/___|";

        // introduction and initial prompt for user input
        System.out.print("Hello! My name is:\n" + name + "\n\n" +
                "What can I do for you today? :)\n" + ">>");
    }

    // private function to print a stream of underscores for partitioning robot conversation
    private static void partition() {
        System.out.println("____________________________________________________________");
    }
}
