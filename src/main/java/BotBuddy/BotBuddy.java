package BotBuddy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BotBuddy {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printUnderscores();
        System.out.println("Starting up...");
        printUnderscores();

        String filePath = "data/taskfile.txt";
        String directoryName = "data";

        File directory = new File(directoryName);
        File taskFile = new File(filePath);

        if (!taskFile.exists()) {
            printUnderscores();
            System.out.println("Task file not found! Creating one...");
            printUnderscores();

            if (!directory.exists()) {
                directory.mkdir();
            }
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating task file... Exiting!");
                return;
            }
        }

        try {
            readTaskFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Error creating task file... Exiting!");
            return;
        }

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

            // write to file here
            try {
                writeTaskFile(filePath);
            } catch (IOException e) {
                System.out.println("Error writing to file!");
            }

        } while (!command.equals("bye"));
    }

    private static void readTaskFile(String filePath) throws FileNotFoundException {
        File taskFile = new File(filePath);
        Scanner taskScanner = new Scanner(taskFile);

        while (taskScanner.hasNext()) {
            String currentTask = taskScanner.nextLine();
            if (currentTask.startsWith("[T]")) {
                currentTask = currentTask.substring(3);
                boolean isDone = false;
                if (currentTask.startsWith("[X]")) {
                    isDone = true;
                }
                currentTask = currentTask.substring(3);
                addTodoFromFile(currentTask);
                if (isDone) {
                    int noOfTasks = Task.getNoOfTasks();
                    markTaskFromFile(Integer.toString(noOfTasks));
                }
            } else if (currentTask.startsWith("[E]")) {
                currentTask = currentTask.substring(3);
                boolean isDone = false;
                if (currentTask.startsWith("[X]")) {
                    isDone = true;
                }
                currentTask = currentTask.substring(3);
                addEventFromFile(currentTask);
                if (isDone) {
                    int noOfTasks = Task.getNoOfTasks();
                    markTaskFromFile(Integer.toString(noOfTasks));
                }
            } else if (currentTask.startsWith("[D]")) {
                currentTask = currentTask.substring(3);
                boolean isDone = false;
                if (currentTask.startsWith("[X]")) {
                    isDone = true;
                }
                currentTask = currentTask.substring(3);
                addDeadlineFromFile(currentTask);
                if (isDone) {
                    int noOfTasks = Task.getNoOfTasks();
                    markTaskFromFile(Integer.toString(noOfTasks));
                }
            } else {
                // this should not run
                System.out.println("Corrupted data file!");
                return;
            }
        }
    }

    private static void writeTaskFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        StringBuilder taskData = new StringBuilder();
        int noOfTasks = Task.getNoOfTasks();
        for (int i = 0; i < noOfTasks; i++) {

            if (tasks.get(i).getClass() == Todo.class) {
                Todo currentTodo = (Todo) tasks.get(i);
                taskData.append("[T]").append(currentTodo.getStatusIcon());
                taskData.append(currentTodo.getDescription());
                taskData.append(System.lineSeparator());
            } else if (tasks.get(i).getClass() == Event.class) {
                Event currentEvent = (Event) tasks.get(i);
                taskData.append("[E]").append(currentEvent.getStatusIcon());
                taskData.append(currentEvent.getDescription());
                taskData.append(" /from ").append(currentEvent.getFrom());
                taskData.append(" /to ").append(currentEvent.getTo());
                taskData.append(System.lineSeparator());
            } else if (tasks.get(i).getClass() == Deadline.class) {
                Deadline currentDeadline = (Deadline) tasks.get(i);
                taskData.append("[D]").append(currentDeadline.getStatusIcon());
                taskData.append(currentDeadline.getDescription());
                taskData.append(" /by ").append(currentDeadline.getBy());
                taskData.append(System.lineSeparator());
            } else {
                // This should not run
                System.out.println("Fatal error!");
            }
//            taskData.append(tasks.get(i));
//            taskData.append(System.lineSeparator());
        }
        fileWriter.write(taskData.toString());
        fileWriter.close();
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


    public static void addTodoFromFile(String parameters) {
        tasks.add(new Todo(parameters));
    }

    public static void addEventFromFile(String parameters) {
        String[] eventDetails = parameters.split("/from");
        String eventName = eventDetails[0].trim();
        eventDetails = eventDetails[1].split("/to");
        String eventFrom = eventDetails[0].trim();
        String eventTo = eventDetails[1].trim();
        tasks.add(new Event(eventName, eventFrom, eventTo));
    }

    public static void addDeadlineFromFile(String parameters) {
        String[] deadlineDetails = parameters.split("/by");
        String deadlineName = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        tasks.add(new Deadline(deadlineName, deadlineBy));
    }

    public static void markTaskFromFile(String parameters) {
        int taskToMark = Integer.parseInt(parameters) - 1;
        tasks.get(taskToMark).markAsDone();
    }
}
