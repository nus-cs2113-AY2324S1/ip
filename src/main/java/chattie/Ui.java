package chattie;

import chattie.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }


    public static void greetUser() {
        String line;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("\tHello! I'm Chattie! How was your day?");
        printLine();

        line = in.nextLine().toLowerCase();

        printLine();
        if(line.contains("good")) {
            System.out.println("\tGreat to hear that! :D");
        } else if (line.contains("bad")) {
            System.out.println("\tI'm sorry to hear that :(");
        } else {
            System.out.println("\tSounds like you had quite a day");
        }
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    public static String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showError() {
        System.out.println("\tPlease try again");
    }

    public void showError(String message) {
        System.out.println("\tOh no! " + message);
        System.out.println("\tPlease try again");
    }

    public static void printUnmarkMessage(TaskList task, int taskNum) {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + task.getTask(taskNum));
    }

    public static void printAddMessage(Task task, TaskList tasks) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }

    public void printDeleteMessage(TaskList taskList, Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.println("\tNow you have " + taskList.getSize() + " tasks in the list.");
    }

    public void printMarkMessage(TaskList task, int taskNum) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + task.getTask(taskNum));
    }

    public void printTaskList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("\t" + (i+1) + ". ");
            System.out.println(list.get(i));
        }
    }

    public void printFilteredList(ArrayList<Task> list) {
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("\t" + (i+1) + ". ");
            System.out.println(list.get(i));
        }
    }

    public void printExitMessage() {
        System.out.println("\tByeeeee. Hope to see you again soon! :)");
    }

    public void printEchoMessage(String command) {
        System.out.println(command);
    }
}
