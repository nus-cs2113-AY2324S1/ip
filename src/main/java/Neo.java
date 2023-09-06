import java.util.Scanner;
public class Neo {
    public static void printList(Task[] list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(list[i]);
        }
    }

    public static void markTask(String line, Task[] list) {
        String[] words = line.split(" ");
        int listIndex = Integer.parseInt(words[1]) - 1;
        list[listIndex].setDone(true);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("    " + list[listIndex]);
    }

    public static void unmarkTask(String line, Task[] list) {
        String[] words = line.split(" ");
        int listIndex = Integer.parseInt(words[1]) - 1;
        list[listIndex].setDone(false);
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("    " + list[listIndex]);
    }

    public static void addEvent(String line, Task[] list) {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        String description = line.substring(6, fromIndex - 1);
        String from = line.substring(fromIndex + 6, toIndex - 1);
        String to = line.substring(toIndex + 4);
        list[Task.getTotalTasks()] = new Event(description, from, to);
        list[Task.getTotalTasks() - 1].printAddedTask();
    }

    public static void addDeadline(String line, Task[] list) {
        int byIndex = line.indexOf("/by");
        String description = line.substring(9, byIndex - 1);
        String by = line.substring(byIndex + 4);
        list[Task.getTotalTasks()] = new Deadline(description, by);
        list[Task.getTotalTasks() - 1].printAddedTask();
    }

    public static void addTodo(String line, Task[] list) {
        String description = line;
        if (line.startsWith("todo")) {
            description = line.substring(5);
        }
        list[Task.getTotalTasks()] = new Todo(description);
        list[Task.getTotalTasks() - 1].printAddedTask();
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Neo.");
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] list = new Task[100];
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(list);
                line = in.nextLine();
            } else if (line.startsWith("mark")) {
                markTask(line, list);
                line = in.nextLine();
            } else if (line.startsWith("unmark")) {
                unmarkTask(line, list);
                line = in.nextLine();
            } else if (line.startsWith("event")) {
                addEvent(line, list);
                line = in.nextLine();
            } else if (line.startsWith("deadline")) {
                addDeadline(line, list);
                line = in.nextLine();
            } else {
                addTodo(line, list);
                line = in.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
