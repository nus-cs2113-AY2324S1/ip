import java.util.Scanner;
import java.util.Arrays;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }
    public boolean isDone() {
        return isDone;
    }
    public String getDescription() {
        return description;
    }
    public String getTaskType() {
        return "Task";
    }
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String getTaskType() {
        return "Todo";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getTaskType() {
        return "Deadline";
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
    @Override
    public String getTaskType() {
        return "Event";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

public class Duke {
    public void run() {
        String LINE = "__________________________________________\n";
        System.out.println(LINE
                           + "Hello I'm MatinBot\n"
                           + "What can I do for you?\n"
                           + LINE);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;
        String userInput;

        while (true) {
            // Get the user input first
            userInput = scanner.nextLine();
            System.out.println(LINE);

            try {
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    printTasks(tasks, count);
                } else if (userInput.startsWith("mark ")) {
                    markTask(userInput, tasks);
                } else if (userInput.startsWith("unmark ")) {
                    unmarkTask(userInput, tasks);
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.replaceFirst("todo", "").trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("todo");
                    }
                    addTask("todo " + description, tasks, count, "todo");
                    count++;
                } else if (userInput.startsWith("deadline")) {
                    String description = userInput.replaceFirst("deadline", "").trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    addTask("deadline " + description, tasks, count, "deadline");
                    count++;
                } else if (userInput.startsWith("event")) {
                    String description = userInput.replaceFirst("event", "").trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("event");
                    }
                    addTask("event " + description, tasks, count, "event");
                    count++;
//                } else if (userInput.trim().isEmpty()) {
//                    throw new EmptyDescriptionException("task");
                } else {
                    throw new UnknownCommandException();
                }
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(LINE);
        }

        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
        scanner.close();
    }
    // Create a task of a specific type to the tasks array
    private static void addTask(String userInput, Task[] tasks, int count, String type) {
        String description;
        String bracketInfo;

        // Check for any inputs with '/'
        int checkSlash = userInput.indexOf("/");
        if (checkSlash != -1) {
            description = userInput.substring(type.length() + 1, checkSlash).trim();
            bracketInfo = userInput.substring(checkSlash).trim();
        } else {
            description = userInput.substring(type.length() + 1).trim();
            bracketInfo = "";
        }

        if (type.equals("todo")) {
            tasks[count] = new Todo(description);
        } else if (type.equals("deadline")) {
            String by = bracketInfo.replace("/by", "").trim();
            tasks[count] = new Deadline(description, by);
        } else if (type.equals("event")) {
            String from = bracketInfo.split("/from")[1].split("/to")[0].trim();
            String to = bracketInfo.split("/to")[1].trim();
            tasks[count] = new Event(description, from, to);
        }

        System.out.println("Got it. I've added this task:\n"
                + tasks[count]
                + "\nNow you have "
                + (count + 1)
                + " tasks in the list.");
    }

    // Print the task list
    private static void printTasks(Task[] tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
        }
    }

    // Mark a task as done
    private static void markTask(String userInput, Task[] tasks) {
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
        tasks[taskIndex].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + tasks[taskIndex]);
    }

    // Unmark a task
    private static void unmarkTask(String userInput, Task[] tasks) {
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        tasks[taskIndex].markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + tasks[taskIndex]);
    }

    // Custom exception for empty task descriptions
    static class EmptyDescriptionException extends Exception {
        private String taskType;

        public EmptyDescriptionException(String taskType) {
            super("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
            this.taskType = taskType;
        }

        public String getTaskType() {
            return taskType;
        }
    }

    // Custom exception for unknown commands
    class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke(); // Create an instance of the Duke class
        duke.run(); // Run the chatbot
    }
}