import java.util.Scanner;
import java.util.List;

public class Duke {
    static final int LENGTH = 50;

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);

        printHorizontalLines();
        System.out.println("Hi! I'm Joshua");
        System.out.println("What can I do for you?");
        printHorizontalLines();

        while (true) {
            String input = scanner.nextLine().trim();
            String[] inputWords = input.split(" ");
            String command = inputWords[0].toLowerCase();

            switch (command) {
            case "bye":
                return;
            case "list":
                printTaskList(taskList);
                break;
            case "todo":
                addToDoTask(input, inputWords, taskList);
                break;
            case "deadline":
                addDeadline(input, inputWords, taskList);
                break;
            case "event":
                addEvent(input, inputWords, taskList);
                break;
            case "mark":
                markAsDone(inputWords, taskList);
                break;
            case "unmark":
                unmark(inputWords, taskList);
                break;
            default:
                System.out.println("Invalid command. Try again.");
                break;
            }
        }
    }

    public static void addToDoTask(String input, String[] inputWords, TaskList taskList) {
        if (inputWords != null) {
            String todoDescription = input.substring(5);
            ToDo todo = new ToDo(todoDescription);
            taskList.addTask(todo);
            printAddedTask(todo, taskList);
        } else {
            System.out.println("Please provide a description for the 'todo' task.");
        }
    }

    public static void addDeadline(String input, String[] inputWords, TaskList taskList) {
        if (inputWords.length > 2 && input.contains("/by")) {
            int byIndex = input.indexOf("/by");
            String deadlineDescription = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            Deadline deadline = new Deadline(deadlineDescription, by);
            taskList.addTask(deadline);
            printAddedTask(deadline, taskList);
        } else {
            System.out.println("Invalid 'deadline' format. Use 'deadline <description> /by <date>'");
        }
    }

    public static void addEvent(String input, String[] inputWords, TaskList taskList) {
        if (inputWords.length > 3 && input.contains("/from") && input.contains("/to")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            String eventDescription = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 6, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            Event event = new Event(eventDescription, from, to);
            taskList.addTask(event);
            printAddedTask(event, taskList);
        } else {
            System.out.println("Invalid 'event' format. Use 'event <description> /from <start time> /to <end time>'");
        }
    }

    public static void markAsDone(String[] inputWords, TaskList taskList) {
        if (inputWords.length == 2) {
            try {
                int taskIndex = Integer.parseInt(inputWords[1]);
                taskList.markTaskDone(taskIndex);
                printMarkedTask(taskIndex, taskList);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please use 'mark <number>'.");
            }
        } else {
            System.out.println("Invalid input. Please use 'mark <number>'.");
        }
    }

    public static void unmark(String[] inputWords, TaskList taskList) {
        if (inputWords.length == 2) {
            try {
                int taskIndex = Integer.parseInt(inputWords[1]);
                taskList.unmarkTask(taskIndex);
                printUnmarkedTask(taskIndex, taskList);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please use 'unmark + number'.");
            }
        } else {
            System.out.println("Invalid input. Please use 'unmark + number'.");
        }
    }

    private static void printTaskList(TaskList taskList) {
        printHorizontalLines();
        System.out.println("Here are the tasks in your list:");
        List<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printHorizontalLines();
    }

    private static void printAddedTask(Task task, TaskList taskList) {
        printHorizontalLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
        printHorizontalLines();
    }

    private static void printMarkedTask(int taskIndex, TaskList taskList) {
        printHorizontalLines();
        List<Task> tasks = taskList.getTasks();
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            System.out.println("Ok! I've marked this task as done:");
            System.out.println(" " + task.toString());
        } else {
            System.out.println("Invalid task index.");
        }
        printHorizontalLines();
    }

    private static void printUnmarkedTask(int taskIndex, TaskList taskList) {
        printHorizontalLines();
        List<Task> tasks = taskList.getTasks();
        if (taskIndex >= 1 && taskIndex <= tasks.size()) {
            Task task = tasks.get(taskIndex - 1);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + task.toString());
        } else {
            System.out.println("Invalid task index.");
        }
        printHorizontalLines();
    }

    private static void printHorizontalLines() {
        for (int i = 0; i < LENGTH; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}

