package duke;

import java.util.Scanner;

/**
 * Represents the user interface for the Duke application.
 * The Ui class handles interactions with the user, including reading commands,
 * printing messages, and displaying task information.
 */
public class Ui {

    private TaskList tasks;
    private Scanner in = new Scanner(System.in);

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    private void println(String str) {
        System.out.println(str);
    }

    /**
    * Prints the introductory message when the application starts.
    */
    public void printIntro() {
        printDivider();
        println("Hello! I'm Itay!");
        println("What can I do for you?");
        printDivider();
    }

    /**
    * Prints the exit message when the application terminates.
    */
    public void printExit() {
        printDivider();
        println("Bye. Hope to see you again soon!");
        printDivider();
    }

    /**
    * Prints the list of tasks, including their descriptions and statuses.
    */
    public void printList() {
        printDivider();
        int numTasks = tasks.getSize();
        if(numTasks == 0) {
            println("You have no tasks in your list."); 
        }
        else {
            println("Here are the tasks in your list:");
            for(int i = 0; i < numTasks; i++) {
                println(tasks.getTaskAt(i).toString());
            }
        }
        printDivider();
    }

    /**
     * Prints the list of tasks that match a specified keyword in their descriptions.
     *
     * @param foundTasks A TaskList containing tasks that match the keyword.
     */
    public void printFound(TaskList foundTasks) {
        printDivider();
        int numTasks = foundTasks.getSize();
        if(numTasks == 0) {
            println("You have no matching tasks in your list.");
        }
        else {
            println("Here are the matching tasks in your list:");
            for(int i = 0; i < numTasks; i++) {
                println(foundTasks.getTaskAt(i).toString());
            }
        }
        printDivider();
    }

    /**
     * Prints a message to indicate that a task has been marked as done.
     *
     * @param taskIdx The index of the task to mark.
     */
    public void printMarked(int taskIdx) {
        printDivider();
        println("Nice! I've marked this task as done:");
        println(tasks.getTaskAt(taskIdx).toString());
        printDivider();
    }

    /**
     * Prints a message to indicate that a task has been unmarked.
     *
     * @param taskIdx The index of the task to unmark.
     */
    public void printUnmarked(int taskIdx) {
        printDivider();
        println("OK, I've marked this task as not done yet:");
        println(tasks.getTaskAt(taskIdx).toString());
        printDivider();
    }

    /**
     * Prints a message to indicate that a task has been deleted from the task list.
     *
     * @param toDelete The task to delete.
     */
    public void printDelete(Task toDelete) {
        printDivider();
        println("Got it. I've removed this task:");
        println(toDelete.toString());
        println("Now you have " + tasks.getSize() + " tasks in the list.");
        printDivider();
    }

    /**
     * Prints a message to indicate that a task has been added to the task list.
     *
     * @param task The task to add.
     */
    public void printAddTask(Task task) {
        printDivider();
        println("Got it. I've added this task:");   
        println(task.toString());   
        println("Now you have " + tasks.getSize() + " tasks in the list.");
        printDivider();   
    }

    /**
    * Prints an exception message.
    *
    * @param exception The exception message to print.
    */
    public void printException(String exception) {
        printDivider();
        println(exception);
        printDivider();
    }

    private void printDivider() {
        String divider = "-".repeat(60);
        println(divider);
    }
}