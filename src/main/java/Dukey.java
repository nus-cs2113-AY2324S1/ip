import java.util.ArrayList;
import java.util.Scanner;

public class Dukey {
    private static void addTodo(String line, ArrayList<Task> tasks) {
        String description = line;
        if (line.startsWith("todo")) {
            final int beginIndex = 5;
            description = line.substring(beginIndex);
        }
        tasks.add(new Todo(description));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void addEvent(String line, ArrayList<Task> tasks) {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        final int beginIndex = 6;
        String from = line.substring(fromIndex + 6, toIndex);
        String to = line.substring(toIndex + 4);
        String description = line.substring(beginIndex, fromIndex);
        tasks.add(new Event(from, to,description));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void addDeadline(String line, ArrayList<Task> tasks) {
        int splitIndex = line.indexOf("/");
        final int beginIndex = 9;
        String description = line.substring(beginIndex, splitIndex);
        String by = line.substring(splitIndex + 4);
        tasks.add(new Deadline(description, by));
        tasks.get(tasks.size() - 1).printAddedTask();
    }

    private static void unmarkTask(String line, ArrayList<Task> tasks) {
        String taskIndex = line.substring(line.length() - 1);
        int taskNum = Integer.parseInt(taskIndex) - 1;
        tasks.get(taskNum).unmarkTask();
    }

    private static void markTask(String line, ArrayList<Task> tasks) {
        if (line.isEmpty()) {
            System.out.println("Error: Empty input");
            return;
        }

        char lastChar = line.charAt(line.length() - 1);

        if (!Character.isDigit(lastChar)) {
            System.out.println("Error: Invalid task index");
            return;
        }
        int taskNum = Integer.parseInt(String.valueOf(lastChar)) - 1;

        if (taskNum < 0 || taskNum >= tasks.size()) {
            System.out.println("Error: Invalid task index");
            return;
        }
        tasks.get(taskNum).markAsDone();
    }


    public static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        for (Task task : tasks) {
            System.out.println((index++) + "." + task);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Dukey\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String line;
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            line = in.nextLine();
            // multiple if-else statements were used instead of a switch statement
            // because when the line starts with a certain prefix,
            // you cannot achieve that directly with a switch statement
            if (line.startsWith("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return;
            } else if (line.equals("list")) {
                printTaskList(tasks);
            } else if (line.startsWith("mark")) {
                markTask(line, tasks);
            } else if (line.startsWith("unmark")) {
                unmarkTask(line, tasks);
            } else if (line.startsWith("deadline")) {
                addDeadline(line, tasks);
            } else if (line.startsWith("event")) {
                addEvent(line, tasks);
            } else {
                addTodo(line, tasks);
            }

        }
    }
}
