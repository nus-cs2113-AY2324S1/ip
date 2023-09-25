package linguobot.command;

import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.task.Task;
import linguobot.task.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static linguobot.file.TaskFile.saveTaskListToFile;

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
    public static void printTaskList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(taskList.get(i));
        }
        printLine();
    }

    public static void markTaskAsDone(ArrayList<Task> taskList, String userInput) {
        try {
            int MARK_START_INDEX = 5;
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
    public static void markTaskAsUndone(ArrayList<Task> taskList, String userInput) {
        try {
            int UNMARK_START_INDEX = 7;
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

    public static void deleteTask(ArrayList<Task> taskList, String userInput) {
        try {
            int DELETE_START_INDEX = 7;
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
                throw new LinguoBotException("Invalid task index. Please provide valid task index" + " < " + (taskList.size() + 1) + " to delete task.");
            }
        } catch (LinguoBotException e) {
            printLine();
            System.out.println("Error: " + e.getMessage());
            printLine();
        }
    }
    public static void addTask(String line, ArrayList<Task> taskList) {
        try {
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
                    throw new LinguoBotException("Invalid input. Please include 'by D/M/YYYY' for deadlines.");
                }
                String description = line.substring(8, indexBy).trim();
                String date = line.substring(indexBy + 2).trim();
                if (!date.matches("\\d{1,2}/\\d{1,2}/\\d{4}") && !date.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2} [APap][Mm]")) {
                    throw new LinguoBotException("Invalid date format. Please use the format 'D/M/YYYY H:MM AM/PM', e.g., '5/4/2023 6:00 PM'");
                } else {
                    taskList.add(new Deadline(description, date));
                }
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
            saveTaskListToFile(taskList);
            System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
            printLine();
        } catch (LinguoBotException e) {
            CommandResponse.printLine();
            System.out.println("Error: " + e.getMessage());
            CommandResponse.printLine();
        }
    }

}
