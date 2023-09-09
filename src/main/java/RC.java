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

    private static void addEvent(String input, ArrayList<Task> tasks) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        final int beginIndex = 6;

        String description = input.substring(beginIndex, fromIndex);
        String from = input.substring(fromIndex + 6, toIndex);
        String to = input.substring(toIndex + 4);

        tasks.add(new Event(description, from, to));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void addDeadline(String input, ArrayList<Task> tasks) {
        int splitIndex = input.indexOf("/");
        final int beginIndex = 9;

        String description = input.substring(beginIndex, splitIndex);
        String by = input.substring(splitIndex + 4);

        tasks.add(new Deadline(description, by));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void unmarkTask(String input, ArrayList<Task> tasks) {
        String taskIndex = input.substring(input.length() - 1);
        int taskNum = Integer.parseInt(taskIndex) - 1;

        tasks.get(taskNum).unmarkTask();
    }

    private static void markTask(String input, ArrayList<Task> tasks) {
        String taskIndex = input.substring(input.length() - 1);
        int taskNum = Integer.parseInt(taskIndex) - 1;

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
