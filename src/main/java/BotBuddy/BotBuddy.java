package BotBuddy;

import java.util.ArrayList;
import java.util.Scanner;

public class BotBuddy {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printUnderscores();
        System.out.println("Hello from BotBuddy!");
        System.out.println("What can I do for you?");
        printUnderscores();
        String input;
        String[] inputArr;
        String command = "";
        String parameters = "";
        Scanner in = new Scanner(System.in);

        do {
            input = in.nextLine().trim();
            inputArr = input.split(" ", 2);
            command = inputArr[0];
            if (inputArr.length == 2) {
                parameters = inputArr[1];
            } else {
                parameters = "";
            }

            switch (command) {
            case "todo":
                addTodo(parameters);
                break;

            case "event":
                addEvent(parameters);
                break;

            case "deadline":
                addDeadline(parameters);
                break;

            case "list":
                listTasks();
                break;

            case "mark":
                markTask(parameters);
                break;

            case "unmark":
                unmarkTask(parameters);
                break;

            case "delete":
                deleteTask(parameters);
                break;

            case "bye":
                exitProgram();
                return;
            default:
                invalidCommand();
                break;
            }
        } while (!command.equals("bye"));
    }

    public static void printUnderscores() {
        System.out.println("____________________________________________________________");
    }

    public static void addTodo(String parameters) {
        try {
            validateInput("todo", parameters);
        } catch (BotBuddyException e) {
            printUnderscores();
            System.out.println(e.getMessage());
            printUnderscores();
            return;
        }
        // add todo
        int noOfTasks = Task.getNoOfTasks();
        tasks.add(new Todo(parameters));
        printUnderscores();
        System.out.println("Got it, I've added this task:");
        System.out.println(tasks.get(noOfTasks));
        printUnderscores();
    }

    public static void addEvent(String parameters) {
        try {
            validateInput("event", parameters);
        } catch (BotBuddyException e) {
            printUnderscores();
            System.out.println(e.getMessage());
            printUnderscores();
            return;
        }
        // add event
        int noOfTasks = Task.getNoOfTasks();
        String[] eventDetails = parameters.split("/from");
        String eventName = eventDetails[0].trim();
        eventDetails = eventDetails[1].split("/to");
        String eventFrom = eventDetails[0].trim();
        String eventTo = eventDetails[1].trim();
        tasks.add(new Event(eventName, eventFrom, eventTo));
        printUnderscores();
        System.out.println("Got it, I've added this task:");
        System.out.println(tasks.get(noOfTasks));
        printUnderscores();
    }

    public static void addDeadline(String parameters) {
        try {
            validateInput("deadline", parameters);
        } catch (BotBuddyException e) {
            printUnderscores();
            System.out.println(e.getMessage());
            printUnderscores();
            return;
        }
        // add deadline
        int noOfTasks = Task.getNoOfTasks();
        String[] deadlineDetails = parameters.split("/by");
        String deadlineName = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        tasks.add(new Deadline(deadlineName, deadlineBy));
        printUnderscores();
        System.out.println("Got it, I've added this task:");
        System.out.println(tasks.get(noOfTasks));
        printUnderscores();
    }

    public static void listTasks() {
        int noOfTasks = Task.getNoOfTasks();
        if (noOfTasks == 0) {
            printUnderscores();
            System.out.println("There are currently no tasks!");
            printUnderscores();
            return;
        }
        // print out tasks
        printUnderscores();
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        printUnderscores();
    }

    public static void markTask(String parameters) {
        try {
            validateInput("mark", parameters);
        } catch (BotBuddyException e) {
            printUnderscores();
            System.out.println(e.getMessage());
            printUnderscores();
            return;
        }
        int taskToMark = Integer.parseInt(parameters) - 1;
        tasks.get(taskToMark).markAsDone();
        printUnderscores();
        System.out.println("I've marked this task as done:");
        System.out.println(tasks.get(taskToMark));
        printUnderscores();
    }

    public static void unmarkTask(String parameters) {
        try {
            validateInput("unmark", parameters);
        } catch (BotBuddyException e) {
            printUnderscores();
            System.out.println(e.getMessage());
            printUnderscores();
            return;
        }
        int taskToUnmark = Integer.parseInt(parameters) - 1;
        tasks.get(taskToUnmark).markAsUndone();
        printUnderscores();
        System.out.println("I've unmarked this task:");
        System.out.println(tasks.get(taskToUnmark));
        printUnderscores();
    }

    public static void deleteTask(String parameters) {
        try {
            validateInput("delete", parameters);
        } catch (BotBuddyException e) {
            printUnderscores();
            System.out.println(e.getMessage());
            printUnderscores();
            return;
        }
        int taskToDelete = Integer.parseInt(parameters) - 1;
        String tempMessage = String.valueOf(tasks.get(taskToDelete));
        int noOfTasks = Task.getNoOfTasks();
        tasks.remove(taskToDelete);
        Task.setNoOfTasks(noOfTasks - 1);
        printUnderscores();
        System.out.println("I've deleted this task:");
        System.out.println(tempMessage);
        printUnderscores();
    }

    public static void exitProgram() {
        printUnderscores();
        System.out.println("Goodbye, hope to see you again soon!");
        printUnderscores();
    }

    public static void invalidCommand() {
        printUnderscores();
        System.out.println("Invalid command! Supported commands are: " +
                "todo, event, deadline, list, mark, unmark, bye");
        printUnderscores();
    }

    public static void validateInput(String command, String parameters) throws BotBuddyException {
        switch (command) {
        case "todo":
            if (parameters.isEmpty()) {
                throw new BotBuddyException("The description of a todo cannot be empty!");
            }
            break;

        case "event":
            String[] eventDetails = parameters.split("/from");
            if (eventDetails.length < 2) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "event 'Event Name' /from 'Start Time' /to 'End Time'");
            }
            String eventName = eventDetails[0].trim();
            eventDetails = eventDetails[1].split("/to");
            if (eventDetails.length < 2) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "event 'Event Name' /from 'Start Time' /to 'End Time'");
            }
            String eventFrom = eventDetails[0].trim();
            String eventTo = eventDetails[1].trim();
            if (eventName.isEmpty() || eventTo.isEmpty() || eventFrom.isEmpty()) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "event 'Event Name' /from 'Start Time' /to 'End Time'");
            }
            break;

        case "deadline":
            String[] deadlineDetails = parameters.split("/by");
            if (deadlineDetails.length < 2) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "deadline 'Deadline Name' /by 'Due Date'");
            }
            String deadlineName = deadlineDetails[0].trim();
            String deadlineBy = deadlineDetails[1].trim();
            if (deadlineName.isEmpty() || deadlineBy.isEmpty()) {
                throw new BotBuddyException("Please enter in the following format:\n" +
                        "deadline 'Deadline Name' /by 'Due Date'");
            }
            break;

        case "list":
            break;

        case "mark":
            // Fallthrough

        case "unmark":
            // Fallthrough

        case "delete":
            if (parameters.isEmpty()) {
                throw new BotBuddyException("You did not specify which task!");
            }
            int taskToModify;
            try {
                taskToModify = Integer.parseInt(parameters);
            } catch (NumberFormatException e) {
                throw new BotBuddyException("The task specified should be a number!");
            }
            if (taskToModify > Task.getNoOfTasks() || taskToModify < 1) {
                throw new BotBuddyException("There is no such task!");
            }
            break;

        default:
            // this should never run
            throw new BotBuddyException("Error in validateInput function!");
        }
    }
}
