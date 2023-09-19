import exception.IncompleteDescriptionException;
import exception.InvalidActionException;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Scanner;

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
        Task[] tasks = new Task[100];

        printWelcomeMessage();

        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        while (!text.equals("bye")) {
            try {
                inputValidation(text, tasks);
            } catch (InvalidActionException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            } catch (IncompleteDescriptionException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
            text = sc.nextLine();
        }

        printGoodbyeMessage();
    }

    public static void inputValidation (String text, Task[] tasks) throws InvalidActionException, IncompleteDescriptionException {
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
        default:
            throw new InvalidActionException(MESSAGE_UNKNOWN);
        }
    }

    public static void addTodo(Task[] tasks, String todoDescription) throws IncompleteDescriptionException {
        String[] descriptions = todoDescription.split(" ");

        if (descriptions.length < 2) {
            throw new IncompleteDescriptionException(MESSAGE_EMPTY_TODO);
        } else {
            String description = descriptions[1];
            Task todo = new Todo(description);
            tasks[Task.getTaskCount() - 1] = todo;
            printTaskAddedMessage(todo);
        }
    }

    public static void addDeadline(Task[] tasks, String deadlineDescription) {
        String[] descriptions = deadlineDescription.split("/by");
        String description = descriptions[0].trim();
        String date = descriptions[1].trim();

        Task deadline = new Deadline(description, date);
        tasks[Task.getTaskCount()-1] = deadline;
        printTaskAddedMessage(deadline);
    }

    public static void addEvent(Task[] tasks, String eventDescription) {
        String[] descriptions = eventDescription.split("/from");
        String description = descriptions[0].trim();
        String[] eventTime = descriptions[1].split("/to");
        String eventStart = eventTime[0].trim();
        String eventEnd = eventTime[1].trim();

        Task event = new Event(description, eventStart, eventEnd);
        tasks[Task.getTaskCount()-1] = event;
        printTaskAddedMessage(event);
    }

    private static void printTaskCount() {
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
        System.out.println("  " + task.getDescription());
        printTaskCount();
        System.out.println(LINE);
    }

    public static void printList(Task[] tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTaskCount(); i++){
            System.out.println(i + 1 + ". " + tasks[i].getDescription());
        }
        System.out.println(LINE);
    }

    public static void markTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex-1].markTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_MARK);
        System.out.println("  " + tasks[taskIndex-1].getDescription());
        System.out.println(LINE);
    }

    public static void unMarkTask(Task[] tasks, int taskIndex) {
        tasks[taskIndex-1].unMarkTask();
        System.out.println(LINE);
        System.out.println(MESSAGE_UNMARK);
        System.out.println("  " + tasks[taskIndex-1].getDescription());
        System.out.println(LINE);
    }

}