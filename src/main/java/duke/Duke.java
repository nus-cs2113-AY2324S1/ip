package duke;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public void run() {
        String LINE = "__________________________________________\n";
        System.out.println(LINE
                           + "Hello I'm MatinBot\n"
                           + "What can I do for you?\n"
                           + LINE);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new duke.Task[100];
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

        switch (type) {
            case "todo":
                tasks[count] = new Todo(description);
                break;
            case "deadline":
                if (!bracketInfo.startsWith("/by")) {
                    System.out.println("☹ OOPS!!! The deadline task must include '/by' to specify the date.");
                    return;
                }
                String by = bracketInfo.replace("/by", "").trim();
                if (by.isEmpty()) {
                    System.out.println("☹ OOPS!!! The deadline task's date cannot be empty.");
                    return;
                }
                tasks[count] = new Deadline(description, by);
                break;
            case "event":
                if (!bracketInfo.startsWith("/from") || !bracketInfo.contains("/to")) {
                    System.out.println("☹ OOPS!!! The event task must include '/from' and '/to' to specify the date range.");
                    return;
                }
                String from = bracketInfo.split("/from")[1].split("/to")[0].trim();
                String to = bracketInfo.split("/to")[1].trim();
                if (from.isEmpty() || to.isEmpty()) {
                    System.out.println("☹ OOPS!!! The event task's date range cannot be empty.");
                    return;
                }
                tasks[count] = new Event(description, from, to);
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return;
        }

        System.out.println("Got it. I've added this task:\n"
                + tasks[count]
                + "\nNow you have "
                + (count + 1)
                + " tasks in the list.");
    }

    // Print the task list
    private static void printTasks(Task[] tasks, int count) {
        // Checks if there is anything in the list
        if (count == 0) {
            System.out.println("☹ OOPS!!! The list is empty");
            return;
        }
        else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                if (tasks[i] != null) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
            }
        }
    }

    // Mark a task as done
    private static void markTask(String userInput, Task[] tasks) {
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;

        // Check if the task at the index is not null
        if (taskIndex >= 0 && taskIndex < tasks.length && tasks[taskIndex] != null) {
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + tasks[taskIndex]);
        } else {
            System.out.println("Invalid task index. Please provide a valid task number.");
        }
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