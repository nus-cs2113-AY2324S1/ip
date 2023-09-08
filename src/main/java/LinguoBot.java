//package src.main.java;
import java.util.Scanner;
public class LinguoBot {

    private static void printLine() {
        System.out.println("-------------------------");
    }
    private static void printTaskList(Task[] taskList, int itemCount) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemCount; i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList[i]);
        }
        printLine();
    }

    private static void markTaskAsDone(Task[] taskList, int index, int itemCount) throws LinguoBotException {
        if (index >= 0 && index < itemCount && taskList[index] != null) {
            taskList[index].markAsDone();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList[index]);
            printLine();
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (itemCount + 1));
        }
    }

    private static void markTaskAsUndone(Task[] taskList, int index, int itemCount) throws LinguoBotException {
        if (index >= 0 && index < taskList.length && taskList[index] != null) {
            taskList[index].markAsUndone();
            printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList[index]);
            printLine();
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (itemCount + 1));
        }
    }
    public static void printTask(String line, Task[] taskList, int itemCount) throws LinguoBotException {
        if (line.startsWith("todo")) {
            if (line.substring(4).isEmpty()) {
                throw new LinguoBotException("Todo description cannot be empty.");
            }
            taskList[itemCount] = new Todo(line.substring(4));
        } else if (line.startsWith("deadline")) {
            int indexBy = line.indexOf("by");
            if (line.substring(8).isEmpty()) {
                throw new LinguoBotException("Deadline description cannot be empty.");
            }
            if (indexBy == -1) {
                throw new LinguoBotException("Invalid input. Please include 'by' for deadlines.");
            }
            taskList[itemCount] = new Deadline(line.substring(8, indexBy - 1), line.substring(indexBy + 2));
        } else if (line.startsWith("event")) {
            int indexFrom = line.indexOf("from");
            int indexTo = line.indexOf("to", indexFrom);
            if (line.substring(5).isEmpty()) {
                throw new LinguoBotException("Event description cannot be empty.");
            }
            if (indexFrom == -1 || indexTo == -1) {
                throw new LinguoBotException("Invalid input. Please include both 'from' and 'to' for events.");
            }
            taskList[itemCount] = new Event(line.substring(5, indexFrom - 1), line.substring(indexFrom + 4, indexTo),
                line.substring(indexTo + 2));
        } else {
            throw new LinguoBotException("☹ OOPS!!! I'm sorry, but I don't know what that means. Please include either " +
                    "'todo', 'deadline' or 'event' in your input.");
        }
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList[itemCount]);
        System.out.println("Now you have " + Task.numberOfTasks + " tasks in the list.");
        printLine();
    }

    public static void main(String[] args) {
        String logo = " \n" +
                "                                       \n" +
                " __    _                 _____     _   \n" +
                "|  |  |_|___ ___ _ _ ___| __  |___| |_ \n" +
                "|  |__| |   | . | | | . | __ -| . |  _|\n" +
                "|_____|_|_|_|_  |___|___|_____|___|_|  \n" +
                "            |___|                      \n";

        System.out.println("Hello I'm " + logo);
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int itemCount = 0;

        while (true) {
            String line = in.nextLine();

            if (line.equals("list")) {
                printTaskList(taskList, itemCount);
            } else if (line.startsWith("mark")) {
                int MARK_START_INDEX = 5;
                int taskIndex = Integer.parseInt(line.substring(MARK_START_INDEX)) - 1;
                try {
                    markTaskAsDone(taskList, taskIndex, itemCount);
                } catch (LinguoBotException e) {
                    printLine();
                    System.out.println("Error: " + e.getMessage());
                    printLine();
                }
            } else if (line.startsWith("unmark")) {
                int UNMARK_START_INDEX = 7;
                int taskIndex = Integer.parseInt(line.substring(UNMARK_START_INDEX)) - 1;
                try {
                    markTaskAsUndone(taskList, taskIndex, itemCount);
                } catch (LinguoBotException e) {
                    printLine();
                    System.out.println("Error: " + e.getMessage());
                    printLine();
                }
            } else if (line.contains("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else {
                try {
                    printTask(line, taskList, itemCount);
                    itemCount++;
                } catch (LinguoBotException e) {
                    printLine();
                    System.out.println("Error: " + e.getMessage());
                    printLine();
                }
            }
        }
    }

}