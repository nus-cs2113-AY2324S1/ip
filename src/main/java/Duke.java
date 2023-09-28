import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Duke {
    private static final String DATA_DIRECTORY = "./data/";
    private static final String DATA_FILE_PATH = DATA_DIRECTORY + "duke.txt";

    public static void main(String[] args) {
        printWelcomeMessage();

        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        List<Task> tasks = loadTasks();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        startDuke(scanner, tasks);

        saveTasks(tasks);
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

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("En: Error reading tasks from the data file.");
        }
        return tasks;
    }


    private static void saveTasks(List<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH));
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("En: Error saving tasks to the file.");
        }
    }

    private static Task createTaskFromFile(String line) {
        String[] parts = line.split(" ");

        if (parts.length < 3) {
            System.out.println("En: Skipped a line due to incorrect format (not enough components): " + line);
            return null;
        }

        String taskType = parts[0].substring(1, 2);
        int isDone = parts[1].equals("[X]") ? 1 : 0;
        String description = parts[2];

        Task task;

        switch (taskType) {
            case "T":
                task = new ToDo(description, isDone == 1);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("En: Skipped a line due to incorrect Deadline format: " + line);
                    return null;
                }
                String deadline = parts[3].trim();
                task = new Deadline(description, deadline, isDone == 1);
                break;
            case "E":
                if (parts.length < 5) {
                    System.out.println("En: Skipped a line due to incorrect Event format: " + line);
                    return null;
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, from, to, isDone == 1);
                break;
            default:
                System.out.println("En: Skipped a line due to unknown task type: " + line);
                return null;
        }
        return task;
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
