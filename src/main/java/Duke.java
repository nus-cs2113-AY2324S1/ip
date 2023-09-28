import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        List<Task> tasks = new ArrayList<>();

        startDuke(scanner, tasks);
    }

    private static void startDuke(Scanner scanner, List<Task> tasks) {
        String userInput;
        while (true) {
            System.out.print("You: ");
            userInput = scanner.nextLine().trim();
            printLines();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Have a nice day.");
                printLines();
                scanner.close();
                return;
            }
            commands(userInput, tasks);
        }
    }

    private static void commands(String userInput, List<Task> tasks) {
        String[] inputParts = userInput.split(" ", 2);
        String command = inputParts[0].toLowerCase();
        String argument = inputParts.length > 1 ? inputParts[1] : "";

        try {
            switch (command) {
                case "list":
                    handleListCommand(tasks);
                    break;

                case "mark":
                    handleMarkCommand(tasks, argument);
                    break;

                case "delete":
                    handleDeleteCommand(tasks,argument);
                    break;

                default:
                    handleAddTaskMethod(userInput, tasks);
            }
        } catch (EmptyDescriptionException e) {
            System.out.println("En: ☹ OOPS!!! The description of a task cannot be empty.");
            printLines();
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
            printLines();
        }
    }

    private static void handleDeleteCommand(List<Task> tasks, String argument) {
        try {
            int taskNumber = Integer.parseInt(argument);
            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                Task removedTask = tasks.remove(taskNumber - 1);
                System.out.println("En: Noted. I've removed this task:");
                System.out.println("  " + removedTask);
                System.out.println("En: Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("En: Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("En: Invalid task number format. Please enter a valid task number.");
        }
        printLines();
    }


    private static void handleAddTaskMethod(String userInput, List<Task> tasks) throws EmptyDescriptionException, UnknownCommandException {
        Task task = createTask(userInput);
        if (task != null) {
            tasks.add(task);
            System.out.println("En: Yessir. I've added this task:");
            System.out.println("  " + task);
            System.out.println("En: Now you have " + tasks.size() + " tasks in the list.");
            printLines();
        } else {
            throw new UnknownCommandException("En: I don't understand that command.");
        }
    }

    private static void handleMarkCommand(List<Task> tasks, String argument) {
        try {
            int taskNumber = Integer.parseInt(argument);
            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                Task task = tasks.get(taskNumber - 1);
                task.markDone();
                System.out.println("En: I've marked this task as done:");
                System.out.println("  " + task);
            } else {
                System.out.println("En: Invalid task number. Please enter a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("En: Invalid task number format. Please enter a valid task number.");
        }
        printLines();
    }

    private static void handleListCommand(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("En: There are no tasks in your list.");
        } else {
            System.out.println("En: Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        printLines();
    }

    private static Task createTask(String userInput) throws EmptyDescriptionException {
        if (userInput.startsWith("todo ")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new EmptyDescriptionException("En: ☹ OOPS!!! The description of a task cannot be empty.");
            }
            return new ToDo(description, false);
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split("/by");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String by = parts[1].trim();
                if (description.isEmpty() || by.isEmpty()) {
                    throw new EmptyDescriptionException("En: ☹ OOPS!!! The description and deadline cannot be empty.");
                }
                return new Deadline(description, by, false);
            }
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split("/from");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String[] eventDetails = parts[1].split("/to");
                if (eventDetails.length == 2) {
                    String from = eventDetails[0].trim();
                    String to = eventDetails[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new EmptyDescriptionException("En: ☹ OOPS!!! The description, start time, and end time cannot be empty.");
                    }
                    return new Event(description, from, to, false);
                }
            }
        }
        return null;
    }


    private static void printWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm En\nWhat can I do for you?");
        printLines();
    }

    public static void printLines() {
        for (int i = 0; i < 30; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}

class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}
