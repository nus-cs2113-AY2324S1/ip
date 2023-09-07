import java.util.ArrayList;
import java.util.Scanner;

public class Dukey {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "------------------------";

        System.out.println(logo);
        System.out.println("User");
        System.out.println("ToDos: tasks without any date/time attached to it e.g., visit new theme park");
        System.out.println("Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm");
        System.out.println("Events: tasks that start at a specific date/time and ends at a specific date/time");


        System.out.println(line);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println(line);
                if (tasks.isEmpty()) {
                    System.out.println("No tasks in the list.");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                }
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.split("/by");
                String description = parts[0].substring(9).trim();
                String by = parts[1].trim();
                tasks.add(new Deadline(description, by));
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.split("/from | /to ");
                String description = parts[0].substring(6).trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks.add(new Event(description, from, to));
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskIndex));
                } else {
                    System.out.println(line);
                    System.out.println("Invalid task index.");
                }
            } else if (userInput.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsNotDone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(taskIndex));
                } else {
                    System.out.println(line);
                    System.out.println("Invalid task index.");
                }
            } else {
                tasks.add(new Todo(userInput));
                System.out.println(line);
                System.out.println("added: " + userInput);
            }

            System.out.println(line);
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
}








