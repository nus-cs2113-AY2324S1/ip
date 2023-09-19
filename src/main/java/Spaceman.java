import exception.IncompleteDescriptionException;
import exception.InvalidActionException;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Spaceman {
    public static final String LINE = "------------------------------------------------------------";
    public static final String LOGO = "  ____  _____   ___    _____ _____ __    __   ___   __   __\n"
            + "/     /|  __ \\ /   \\  /   __|     |  \\  /  | /   \\ |  \\ |  |\n"
            + "\\   __\\| |__) |  _  \\|   /  |   __|   \\/   |/  _  \\|   \\|  |\n"
            + " \\__   |  ___/  |_|  |  |   |   __|        |  |_|  |       |\n"
            + "/      | |   |   _   |   \\__|     |   __   |   _   |       |\n"
            + "|____ /|_|   |__| |__|\\_____|_____|__|  |__|__| |__|__|\\___|\n";

    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    public static final String MESSAGE_UNKNOWN = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_EMPTY_TODO = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String MESSAGE_EMPTY_DEADLINE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String MESSAGE_EMPTY_EVENT = "☹ OOPS!!! The description of an event cannot be empty.";

    public static void main(String[] args) {
        String filePath = "./data/spaceman.txt";
        ArrayList<Task> tasks = null;
        try {
            tasks = readDataFromFile(filePath);
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
        }

        printWelcomeMessage();
        Scanner input = new Scanner(System.in);

        String text = input.nextLine();
        while (!text.equals("bye")) {
            try {
                inputValidation(text, tasks);
                writeToFile(filePath, tasks);
            } catch (InvalidActionException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            } catch (IncompleteDescriptionException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            } catch (IOException e) {
                System.out.println(LINE);
                System.out.println("Something went wrong: " + e.getMessage());
                System.out.println(LINE);
            }
            text = input.nextLine();
        }
        printGoodbyeMessage();
    }

    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for (Task task : tasks) {
            String output = null;
            boolean isMarked = task.getTaskStatus();
            int markedIndex;

            if (isMarked) {
                markedIndex = 1;
            } else {
                markedIndex = 0;
            }

            if (task instanceof Todo) {
                output = "T | " + Integer.toString(markedIndex) + " | " + task.getDescription();
            } else if (task instanceof Deadline) {
                output = "D | " + Integer.toString(markedIndex) + " | " + task.getDescription()
                + " | " + ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                output = "E | " + Integer.toString(markedIndex) + " | " + task.getDescription()
                        + " | " + ((Event) task).getStart() + " | " + ((Event) task).getEnd();
            }
            fw.write(output);
            fw.write("\n");
        }
        fw.close();
    }

    private static ArrayList<Task> readDataFromFile(String filePath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner data = new Scanner(file);
        while (data.hasNext()) {
            Character taskType = data.toString().charAt(0);
            String[] descriptions = data.nextLine().split("|");
            switch (taskType) {
            case 'T':
                Task todo = new Todo(descriptions[2], Integer.parseInt(descriptions[1]));
                tasks.add(todo);
                break;
            case 'D':
                Task deadline = new Deadline(descriptions[2], descriptions[3], Integer.parseInt(descriptions[1]));
                tasks.add(deadline);
                break;
            case 'E':
                Task event = new Event(descriptions[2], descriptions[3], descriptions[4],
                        Integer.parseInt(descriptions[1]));
                tasks.add(event);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    public static void inputValidation (String text, ArrayList<Task> tasks) throws InvalidActionException,
            IncompleteDescriptionException {
        String[] actionAndDescriptions = text.split(" ");
        String action = actionAndDescriptions[0];

        switch (action){
        case "list":
            printList(tasks);
            break;
        case "mark":
            markTask(tasks, Integer.parseInt(actionAndDescriptions[1]));
            break;
        case "unmark":
            unMarkTask(tasks, Integer.parseInt(actionAndDescriptions[1]));
            break;
        case "todo":
            addTodo(tasks, text);
            break;
        case "deadline":
            addDeadline(tasks, text);
            break;
        case "event":
            addEvent(tasks, text);
            break;
        case "delete":
            deleteTask(tasks, Integer.parseInt(actionAndDescriptions[1]));
            break;
        default:
            throw new InvalidActionException(MESSAGE_UNKNOWN);
        }
    }

    public static void addTodo(ArrayList<Task> tasks, String todoDescription) throws IncompleteDescriptionException {
        String[] descriptions = todoDescription.split(" ");

        if (descriptions.length < 2) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_TODO);
        } else {
            String description = descriptions[1];
            Task todo = new Todo(description);
            tasks.add(todo);
            printTaskAddedMessage(todo);
        }
    }

    public static void addDeadline(ArrayList<Task> tasks, String deadlineDescription) {
        String[] descriptions = deadlineDescription.split("/by");
        String description = descriptions[0].trim();
        String date = descriptions[1].trim();

        Task deadline = new Deadline(description, date);
        tasks.add(deadline);
        printTaskAddedMessage(deadline);
    }

    public static void addEvent(ArrayList<Task> tasks, String eventDescription) {
        String[] descriptions = eventDescription.split("/from");
        String description = descriptions[0].trim();
        String[] eventTime = descriptions[1].split("/to");
        String eventStart = eventTime[0].trim();
        String eventEnd = eventTime[1].trim();

        Task event = new Event(description, eventStart, eventEnd);
        tasks.add(event);
        printTaskAddedMessage(event);
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) {
        Task task = tasks.get(taskIndex-1);
        tasks.remove(task);
        Task.decreaseTaskCountByOne();
        printTaskRemovedMessage(task);
    }

    private static void printTaskCount( ) {
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE);
        System.out.println("Hello! I'm Spaceman");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    private static void printGoodbyeMessage() {
        System.out.println(LINE);
        System.out.println(MESSAGE_BYE);
        System.out.println(LINE);
    }

    private static void printTaskAddedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.getDetails());
        printTaskCount();
        System.out.println(LINE);
    }

    private static void printTaskRemovedMessage(Task task) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.getDescription());
        printTaskCount();
        System.out.println(LINE);
    }

    public static void printList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.getDetails());
        }
        System.out.println(LINE);
    }

    public static void markTask(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex-1).markTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_MARK);
        System.out.println("  " + tasks.get(taskIndex-1).getDetails());
        System.out.println(LINE);
    }

    public static void unMarkTask(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex-1).unMarkTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_UNMARK);
        System.out.println("  " + tasks.get(taskIndex-1).getDetails());
        System.out.println(LINE);
    }

}