package linguobot;

import linguobot.task.Task;
import linguobot.task.Todo;
import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.command.LinguoBotException;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

import static linguobot.file.taskFile.*;

public class LinguoBot {

    private static void printLine() {
        System.out.println("-------------------------");
    }
    private static void printTaskList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i));
        }
        printLine();
    }

    private static void markTaskAsDone(ArrayList<Task> taskList, int index, String taskFile) throws LinguoBotException {
        if (index >= 0 && index < taskList.size()) {
            if (taskList.get(index).getStatusIcon().equals("X")) {
                throw new LinguoBotException("Task has already been marked.");
            } else {
                taskList.get(index).markAsDone();
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index));
                printLine();
                saveTaskListToFile(taskFile, taskList);
            }
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1));
        }
    }

    private static void markTaskAsUndone(ArrayList<Task> taskList, int index, String taskFile) throws LinguoBotException {
        if (index >= 0 && index < taskList.size() && taskList.get(index) != null) {
            if (taskList.get(index).getStatusIcon().equals(" ")) {
                throw new LinguoBotException("Task has already been unmarked.");
            } else {
                taskList.get(index).markAsUndone();
                printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskList.get(index));
                printLine();
                saveTaskListToFile(taskFile, taskList);
            }
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1));
        }
    }

    private static void deleteTask(ArrayList<Task> taskList, int index, String taskFile) throws LinguoBotException {
        if (index >= 0 && index < taskList.size()) {
            printLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskList.get(index));
            System.out.println("Now you have " + (taskList.size() - 1) + " task(s) in the list.");
            printLine();
            taskList.remove(index);
            saveTaskListToFile(taskFile, taskList);
        } else {
            throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1) + " to delete task.");
        }
    }
    public static void printTask(String line, ArrayList<Task> taskList, String taskFile) throws LinguoBotException{
        if (line.startsWith("todo")) {
            if (line.substring(4).isEmpty()) {
                throw new LinguoBotException("Todo description cannot be empty.");
            }
            taskList.add(new Todo(line.substring(4)));
        } else if (line.startsWith("deadline")) {
            int indexBy = line.indexOf("by");
            if (line.substring(8).isEmpty()) {
                throw new LinguoBotException("Deadline description cannot be empty.");
            }
            if (indexBy == -1) {
                throw new LinguoBotException("Invalid input. Please include 'by' for deadlines.");
            }
            taskList.add(new Deadline(line.substring(8, indexBy - 1), line.substring(indexBy + 2)));
        } else if (line.startsWith("event")) {
            int indexFrom = line.indexOf("from");
            int indexTo = line.indexOf("to", indexFrom);
            if (line.substring(5).isEmpty()) {
                throw new LinguoBotException("Event description cannot be empty.");
            }
            if (indexFrom == -1 || indexTo == -1) {
                throw new LinguoBotException("Invalid input. Please include both 'from' and 'to' for events.");
            }
            taskList.add(new Event(line.substring(5, indexFrom - 1), line.substring(indexFrom + 4, indexTo),
                    line.substring(indexTo + 2)));
        } else {
            throw new LinguoBotException("☹ OOPS!!! I'm sorry, but I don't know what that means. " +
                    "\nIf you wish to input a new task: \n" +
                    "Please include either 'todo', 'deadline' or 'event' in your input. " +
                    "\nOtherwise, if you wish to mark/unmark a task: \n" +
                    "Please include either 'mark' or 'unmark', followed by the task index in your input.");
        }
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        saveTaskListToFile(taskFile, taskList);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
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

        String taskFile = "src/task.txt";
        ArrayList<Task> taskList;

        try {
            taskList = loadTasksFromFile(taskFile); // Load tasks from the file
            printFileContents(taskFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            taskList = new ArrayList<>(); // Create a new task list if the file doesn't exist
        }

        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();

            if (line.equals("list")) {
                printTaskList(taskList);
            } else if (line.startsWith("mark")) {
                int MARK_START_INDEX = 5;
                int taskIndex = Integer.parseInt(line.substring(MARK_START_INDEX)) - 1;
                try {
                    markTaskAsDone(taskList, taskIndex, taskFile);
                } catch (LinguoBotException e) {
                    printLine();
                    System.out.println("Error: " + e.getMessage());
                    printLine();
                }
            } else if (line.startsWith("unmark")) {
                int UNMARK_START_INDEX = 7;
                int taskIndex = Integer.parseInt(line.substring(UNMARK_START_INDEX)) - 1;
                try {
                    markTaskAsUndone(taskList, taskIndex, taskFile);
                } catch (LinguoBotException e) {
                    printLine();
                    System.out.println("Error: " + e.getMessage());
                    printLine();
                }
            } else if (line.contains("delete")) {
                int DELETE_START_INDEX = 7;
                int taskIndex = Integer.parseInt(line.substring(DELETE_START_INDEX)) - 1;
                try {
                    deleteTask(taskList, taskIndex, taskFile);
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
                    printTask(line, taskList, taskFile);
                } catch (LinguoBotException e) {
                    printLine();
                    System.out.println("Error: " + e.getMessage());
                    printLine();
                }
            }
        }
    }

}
