package linguobot.command;

import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.task.Task;
import linguobot.task.Todo;

import java.util.ArrayList;

import static linguobot.file.TaskFile.saveTaskListToFile;

/**
 * The `CommandResponse` class contains methods for handling user commands and providing responses.
 * It includes methods for printing messages, displaying tasks, marking tasks as done/undone,
 * deleting tasks, adding tasks, and finding tasks.
 */
public class CommandResponse {
    public static void printLine() {
        System.out.println("===================================================");
    }

    public static void displayWelcomeMessage() {
        String logo = " \n" +
                "                                       \n" +
                " __    _                 _____     _   \n" +
                "|  |  |_|___ ___ _ _ ___| __  |___| |_ \n" +
                "|  |__| |   | . | | | . | __ -| . |  _|\n" +
                "|_____|_|_|_|_  |___|___|_____|___|_|  \n" +
                "            |___|                      \n";

        System.out.println("Hello I'm " + logo);
        System.out.println("What can I do for you?");
    }
    public static void displayGoodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Returns list of tasks.
     * @param taskList ArrayList of tasks.
     */
    public static void printTaskList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i));
        }
        printLine();
    }

    /**
     * Marks a task as done by changing its status icon to 'X'.
     * @param taskList ArrayList of tasks.
     * @param userInput user's command input.
     */
    public static void markTaskAsDone(ArrayList<Task> taskList, String userInput) {
        try {
            int MARK_START_INDEX = 5;
            if (userInput.length() <= MARK_START_INDEX) {
                throw new LinguoBotException("Invalid input format. Please provide a task index to mark.");
            }
            int taskIndex = Integer.parseInt(userInput.substring(MARK_START_INDEX)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                if (taskList.get(taskIndex).getStatusIcon().equals("X")) {
                    throw new LinguoBotException("Task has already been marked.");
                } else {
                    taskList.get(taskIndex).markAsDone();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(taskIndex));
                    printLine();
                    saveTaskListToFile(taskList);
                }
            } else {
                throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1));
            }
        } catch (LinguoBotException e) {
            printLine();
            System.out.println("Error: " + e.getMessage());
            printLine();
        }
    }

    /**
     * Marks a task as undone by changing its status icon to ' ' .
     * @param taskList ArrayList of tasks.
     * @param userInput user's command input.
     */
    public static void markTaskAsUndone(ArrayList<Task> taskList, String userInput) {
        try {
            int UNMARK_START_INDEX = 7;
            if (userInput.length() <= UNMARK_START_INDEX) {
                throw new LinguoBotException("Invalid input format. Please provide a task index to unmark.");
            }
            int taskIndex = Integer.parseInt(userInput.substring(UNMARK_START_INDEX)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size() && taskList.get(taskIndex) != null) {
                if (taskList.get(taskIndex).getStatusIcon().equals(" ")) {
                    throw new LinguoBotException("Task has already been unmarked.");
                } else {
                    taskList.get(taskIndex).markAsUndone();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskList.get(taskIndex));
                    printLine();
                    saveTaskListToFile(taskList);
                }
            } else {
                throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1));
            }
        } catch (LinguoBotException e) {
            printLine();
            System.out.println("Error: " + e.getMessage());
            printLine();
        }
    }

    /** Deletes a task from taskList.
     * @param taskList ArrayList of tasks.
     * @param userInput user's command input.
     */
    public static void deleteTask(ArrayList<Task> taskList, String userInput) {
        try {
            int DELETE_START_INDEX = 7;
            if (userInput.length() <= DELETE_START_INDEX) {
                throw new LinguoBotException("Invalid input format. Please provide a task index to delete.");
            }
            int taskIndex = Integer.parseInt(userInput.substring(DELETE_START_INDEX)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                printLine();
                System.out.println("Noted. I've removed this task:");
                System.out.println(taskList.get(taskIndex));
                System.out.println("Now you have " + (taskList.size() - 1) + " task(s) in the list.");
                printLine();
                taskList.remove(taskIndex);
                saveTaskListToFile(taskList);
            } else {
                if (taskList.isEmpty()) {
                    throw new LinguoBotException("Task list is empty.");
                }
                throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1) + " to delete task.");
            }
        } catch (LinguoBotException e) {
            printLine();
            System.out.println("Error: " + e.getMessage());
            printLine();
        }
    }

    /** Adds a task to taskList.
     * @param taskList ArrayList of tasks.
     * @param userInput user's command input.
     */
    public static void addTask(ArrayList<Task> taskList, String userInput) {
        try {
            if (userInput.startsWith("todo")) {
                if (userInput.substring(4).isEmpty()) {
                    throw new LinguoBotException("Todo description cannot be empty.");
                }
                taskList.add(new Todo(userInput.substring(4).trim()));
            } else if (userInput.startsWith("deadline")) {
                int indexBy = userInput.indexOf("by");
                if (userInput.substring(8).isEmpty()) {
                    throw new LinguoBotException("Deadline description cannot be empty.");
                }
                if (indexBy == -1) {
                    throw new LinguoBotException("Invalid input. Please include 'by D/M/YYYY' for deadlines.");
                }
                String description = userInput.substring(8, indexBy).trim();
                String date = userInput.substring(indexBy + 2).trim();
                if (!date.matches("\\d{1,2}/\\d{1,2}/\\d{4}") && !date.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [APap][Mm]")) {
                    throw new LinguoBotException("Invalid date format. Please use the format 'D/M/YYYY H:MM AM/PM', e.g., '5/4/2023 6:00 PM'");
                } else {
                    taskList.add(new Deadline(description, date));
                }
            } else if (userInput.startsWith("event")) {
                int indexFrom = userInput.indexOf("from");
                int indexTo = userInput.indexOf("to", indexFrom);
                if (userInput.substring(5).isEmpty()) {
                    throw new LinguoBotException("Event description cannot be empty.");
                }
                if (indexFrom == -1 || indexTo == -1) {
                    throw new LinguoBotException("Invalid input. Please include both 'from' and 'to' for events.");
                }
                taskList.add(new Event(userInput.substring(5, indexFrom - 1).trim(), userInput.substring(indexFrom + 4, indexTo),
                        userInput.substring(indexTo + 2)));
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
            saveTaskListToFile(taskList);
            System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
            printLine();
        } catch (LinguoBotException e) {
            CommandResponse.printLine();
            System.out.println("Error: " + e.getMessage());
            CommandResponse.printLine();
        }
    }

    /** Finds tasks that match a given keyword and displays them.
     * @param taskList ArrayList of tasks.
     * @param userInput user's command input.
     */
    public static void findTask(ArrayList<Task> taskList, String userInput) {
        try {
            int FIND_TASK_INDEX = 5;
            if (userInput.length() <= FIND_TASK_INDEX) {
                throw new LinguoBotException("Invalid format. Please enter keyword after 'find'");
            }
            String keyword = userInput.substring(FIND_TASK_INDEX);
            boolean found = false;
            CommandResponse.printLine();
            for (Task task : taskList) {
                if (task.getDescription().contains(keyword)) {
                    if (!found) {
                        System.out.println("Here are the matching tasks in your list:");
                        found = true;
                    }
                    System.out.println(task);
                }
            }
            if (!found) {
                System.out.println("No such task found.");
            }
            CommandResponse.printLine();
        } catch (LinguoBotException e) {
            printLine();
            System.out.println("Error: " + e.getMessage());
            printLine();
        }
    }

}
