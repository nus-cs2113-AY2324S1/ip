import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printUnderscores();
        System.out.println("Hello from BotBuddy!");
        System.out.println("What can I do for you?");
        printUnderscores();
        Task[] tasks = new Task[100];
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
            }

            switch (command) {
            case "todo":
                addTodo(tasks, parameters);
                break;

            case "event":
                addEvent(tasks, parameters);
                break;

            case "deadline":
                addDeadline(tasks, parameters);
                break;

            case "list":
                listTasks(tasks);
                break;

            case "mark":
                markTask(tasks, parameters);
                break;

            case "unmark":
                unmarkTask(tasks, parameters);
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

    public static void addTodo(Task[] tasks, String parameters) {
        // add todo
        int noOfTasks = Task.getNoOfTasks();
        tasks[noOfTasks] = new Todo(parameters);
        printUnderscores();
        System.out.println("Got it, I've added this task:");
        System.out.println(tasks[noOfTasks]);
        printUnderscores();
    }

    public static void addEvent(Task[] tasks, String parameters) {
        // add event
        int noOfTasks = Task.getNoOfTasks();
        String[] eventDetails = parameters.split("/from");
        String eventName = eventDetails[0].trim();
        eventDetails = eventDetails[1].split("/to");
        String eventFrom = eventDetails[0].trim();
        String eventTo = eventDetails[1].trim();
        tasks[noOfTasks] = new Event(eventName, eventFrom, eventTo);
        printUnderscores();
        System.out.println("Got it, I've added this task:");
        System.out.println(tasks[noOfTasks]);
        printUnderscores();
    }

    public static void addDeadline(Task[] tasks, String parameters) {
        // add deadline
        int noOfTasks = Task.getNoOfTasks();
        String[] deadlineDetails = parameters.split("/by");
        String deadlineName = deadlineDetails[0].trim();
        String deadlineBy = deadlineDetails[1].trim();
        tasks[noOfTasks] = new Deadline(deadlineName, deadlineBy);
        printUnderscores();
        System.out.println("Got it, I've added this task:");
        System.out.println(tasks[noOfTasks]);
        printUnderscores();
    }

    public static void listTasks(Task[] tasks) {
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
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        printUnderscores();
    }

    public static void markTask(Task[] tasks, String parameters) {
        int taskToMark = Integer.parseInt(parameters) - 1;
        tasks[taskToMark].markAsDone();
        printUnderscores();
        System.out.println("I've marked this task as done:");
        System.out.println(tasks[taskToMark]);
        printUnderscores();
    }

    public static void unmarkTask(Task[] tasks, String parameters) {
        int taskToUnmark = Integer.parseInt(parameters) - 1;
        tasks[taskToUnmark].markAsUndone();
        printUnderscores();
        System.out.println("I've unmarked this task:");
        System.out.println(tasks[taskToUnmark]);
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
}
