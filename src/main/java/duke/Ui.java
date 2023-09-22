package duke;

import java.util.Scanner;

public class Ui {

    private TaskList tasks;
    private Scanner in = new Scanner(System.in);

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void println(String str) {
        System.out.println(str);
    }

    public void printIntro() {
        printDivider();
        println("Hello! I'm Itay!");
        println("What can I do for you?");
        printDivider();
    }

    public void printExit() {
        printDivider();
        println("Bye. Hope to see you again soon!");
        printDivider();
    }

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

    public void printMarked(int taskIdx) {
        printDivider();
        println("Nice! I've marked this task as done:");
        println(tasks.getTaskAt(taskIdx).toString());
        printDivider();
    }

    public void printUnmarked(int taskIdx) {
        printDivider();
        println("OK, I've marked this task as not done yet:");
        println(tasks.getTaskAt(taskIdx).toString());
        printDivider();
    }

    public void printDelete(Task toDelete) {
        printDivider();
        println("Got it. I've removed this task:");
        println(toDelete.toString());
        println("Now you have " + tasks.getSize() + " tasks in the list.");
        printDivider();
    }

    public void printAddTask(Task task) {
        printDivider();
        println("Got it. I've added this task:");   
        println(task.toString());   
        println("Now you have " + tasks.getSize() + " tasks in the list.");
        printDivider();   
    }

    public void printException(String exception) {
        printDivider();
        println(exception);
        printDivider();

    }

    public void printDivider() {
        String divider = "-".repeat(60);
        println(divider);
    }
}
