import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static List<Task> tasks = new ArrayList<>();

    public static void printHorizontalLines(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printList(int length){
        printHorizontalLines(length);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String status = task.isDone() ? "[X]" : "[ ]";
            System.out.print((i + 1) + ". ");
            if (task instanceof ToDo) {
                System.out.println("[T] " + status + " " + task.getName());
            } else if (task instanceof Deadline) {
                System.out.println("[D] " + status + " " + task.getName() + " (by: " + ((Deadline) task).getBy() + ")");
            } else if (task instanceof Event) {
                System.out.println("[E] " + status + " " + task.getName() + " (from: " + ((Event) task).getFrom() + " to: " + ((Event) task).getTo() + ")");
            }
        }
        printHorizontalLines(length);
    }

    public static void printAddedTask(Task task, int length) {
        printHorizontalLines(length);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLines(length);
    }

    public static boolean isValidIndex(int index) {
        return (index >= 1 && index <= tasks.size());
    }

    public static void markAsDone(int taskIndex, int length) {
        if (isValidIndex(taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);
            printHorizontalLines(length);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + task.toString());
            printHorizontalLines(length);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void unmark(int taskIndex, int  length) {
        if (isValidIndex(taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.setDone(false);
            printHorizontalLines(length);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" [ ] " + task.getName());
            printHorizontalLines(length);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void main(String[] args) {
        int horizontalLineLength = 50;
        printHorizontalLines(horizontalLineLength);
        System.out.println("Hi! I'm Joshua");
        System.out.println("What can I do for you?");
        printHorizontalLines(horizontalLineLength);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            int taskIndex;

            switch (input) {
            case "list":
                printList(horizontalLineLength);
                break;
            case "mark ":
                taskIndex = Integer.parseInt(input.substring(5));
                markAsDone(taskIndex, horizontalLineLength);
                break;
            case "unmark ":
                taskIndex = Integer.parseInt(input.substring(7));
                unmark(taskIndex, horizontalLineLength);
                break;
            case "bye":
                return;
            default:
                String[] inputWords = input.split(" ");
                if (inputWords.length > 0) {
                    String command = inputWords[0].toLowerCase();
                    switch (command) {
                    case "todo":
                        if (inputWords.length > 1) {
                            String todoDescription = input.substring(5);
                            ToDo todo = new ToDo(todoDescription);
                            tasks.add(todo);
                            printAddedTask(todo, horizontalLineLength);
                        } else {
                            System.out.println("Please provide a description for the 'todo' task.");
                        }
                        break;
                    case "deadline":
                        if (inputWords.length > 2 && input.contains("/by")) {
                            int byIndex = input.indexOf("/by");
                            String deadlineDescription = input.substring(9, byIndex).trim();
                            String by = input.substring(byIndex + 3).trim();
                            Deadline deadline = new Deadline(deadlineDescription, by);
                            tasks.add(deadline);
                            printAddedTask(deadline, horizontalLineLength);
                        } else {
                            System.out.println("Invalid 'deadline' format. Use 'deadline <description> /by <date>'");
                        }
                        break;
                    case "event":
                        if (inputWords.length > 3 && input.contains("/from") && input.contains("/to")) {
                            int fromIndex = input.indexOf("/from");
                            int toIndex = input.indexOf("/to");
                            String eventDescription = input.substring(6, fromIndex).trim();
                            String from = input.substring(fromIndex + 6, toIndex).trim();
                            String to = input.substring(toIndex + 3).trim();
                            Event event = new Event(eventDescription, from, to);
                            tasks.add(event);
                            printAddedTask(event, horizontalLineLength);
                        } else {
                            System.out.println("Invalid 'event' format. Use 'event <description> /from <start time> /to <end time>'");
                        }
                        break;
                    default:
                        System.out.println("Invalid command. Please enter a valid command.");
                        break;
                    }
                }
                break;
            }
        }
    }
}
