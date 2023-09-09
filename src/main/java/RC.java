import java.util.ArrayList;
import java.util.Scanner;

public class RC {
    private static void addTodo(String input, ArrayList<Task> tasks) throws RCException {
        final int beginIndex = 4;
        String description = input.substring(beginIndex).trim();
        if (description.isEmpty()) {
            String errorMessage = "\tOOPS!!! The description of a todo cannot be empty.";
            throw new RCException(errorMessage);
        }

        tasks.add(new Todo(description));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void addEvent(String input, ArrayList<Task> tasks) throws RCException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        final int beginIndex = 5;
        final int fromStringLength = 5;
        final int toStringLength = 3;
        if (fromIndex == -1 || toIndex == -1) {
            String errorMessage = "\tOOPS!!! Please include /from and /to for the start and end time.";
            throw new RCException(errorMessage);
        }

        String description = input.substring(beginIndex, fromIndex).trim();
        String from = input.substring(fromIndex + fromStringLength, toIndex).trim();
        String to = input.substring(toIndex + toStringLength).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            String errorMessage = "\tOOPS!!! Please ensure description, start and end time are filled.";
            throw new RCException(errorMessage);
        }
        tasks.add(new Event(description, from, to));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void addDeadline(String input, ArrayList<Task> tasks) throws RCException {
        int splitIndex = input.indexOf("/by");
        final int beginIndex = 8;
        final int byStringLength = 3;
        if (splitIndex == -1) {
            String errorMessage = "\tOOPS!!! Please include /by followed by the deadline. (eg. /by Monday)";
            throw new RCException(errorMessage);
        }

        String description = input.substring(beginIndex, splitIndex).trim();
        String by = input.substring(splitIndex + byStringLength).trim();
        if (description.isEmpty() || by.isEmpty()) {
            String errorMessage = "\tOOPS!!! Please ensure description and deadline are filled.";
            throw new RCException(errorMessage);
        }
        
        tasks.add(new Deadline(description, by));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void unmarkTask(String input, ArrayList<Task> tasks) throws RCException {
        String taskIndex = input.substring(input.length() - 1);
        int taskNum;
        try {
            taskNum = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            String errorMessage = "\tOOPS!!! Please enter a valid integer.";
            throw new RCException(errorMessage);
        }

        if (!Task.isValidIndex(taskNum)) {
            String errorMessage = "\tOOPS!!! Index is out of range of list.";
            throw new RCException(errorMessage);
        }

        tasks.get(taskNum).unmarkTask();
    }

    private static void markTask(String input, ArrayList<Task> tasks) throws RCException {
        String taskIndex = input.substring(input.length() - 1);
        int taskNum;
        try {
            taskNum = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            String errorMessage = "\tOOPS!!! Please enter a valid integer.";
            throw new RCException(errorMessage);
        }

        if (!Task.isValidIndex(taskNum)) {
            String errorMessage = "\tOOPS!!! Index is out of range of list.";
            throw new RCException(errorMessage);
        }

        tasks.get(taskNum).markAsDone();
    }

    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            input = in.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    printTaskList(tasks);
                } else if (input.startsWith("mark")) {
                    markTask(input, tasks);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input, tasks);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input, tasks);
                } else if (input.startsWith("event")) {
                    addEvent(input, tasks);
                } else if (input.startsWith("todo")) {
                    addTodo(input, tasks);
                } else {
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (RCException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }
}
