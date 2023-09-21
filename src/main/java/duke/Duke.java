package duke;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String DATA_FILE_PATH = "./data/duke.txt";

    public void run() {
        // Check if the data directory exists; if not, create it
        File dataDirectory = new File(DATA_DIRECTORY_PATH);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        // Check if the data file exists; if not, create it
        File dataFile = new File(DATA_FILE_PATH);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating the data file: " + e.getMessage());
            }
        }
        String LINE = "__________________________________________\n";
        System.out.println(LINE
                + "Hello I'm MatinBot\n"
                + "What can I do for you?\n"
                + LINE);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasks();
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
                } else if (userInput.startsWith("mark")) {
                    markTask(userInput, tasks, count);
                } else if (userInput.startsWith("unmark")) {
                    unmarkTask(userInput, tasks, count);
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.replaceFirst("todo", "").trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("todo");
                    }
                    addTask("todo " + description, tasks, "todo");
                    count++;
                } else if (userInput.startsWith("deadline")) {
                    String description = userInput.replaceFirst("deadline", "").trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    // Check for /by in the description
                    if (!description.contains("/by")) {
                        System.out.println("☹ OOPS!!! The deadline task must include '/by' to specify the date.");
                    } else {
                        addTask("deadline " + description, tasks, "deadline");
                        count++;
                    }
                } else if (userInput.startsWith("event")) {
                    String description = userInput.replaceFirst("event", "").trim();
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("event");
                    }
                    // Check for /from and /to in the description
                    if (!description.contains("/from") || !description.contains("to")) {
                        System.out.println("☹ OOPS!!! The event task must include '/from' and '/to' to specify the date range.");
                    } else {
                        addTask("event " + description, tasks, "event");
                        count++;
                    }
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput, tasks);
                }else {
                    throw new UnknownCommandException();
                }
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(LINE);
        }

        saveTasks(tasks);
        System.out.println("Bye. Hope to see you again soon!\n" + LINE);
        scanner.close();
    }
    // Create a task of a specific type to the tasks array
    private static void addTask(String userInput, ArrayList<Task> tasks, String type) {
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

        // Checks the task type
        switch (type) {
            case "todo":
                tasks.add(new Todo(description));
                break;
            case "deadline":
                String by = bracketInfo.replace("/by", "").trim();
                tasks.add(new Deadline(description, by));
                break;
            case "event":
                String from = bracketInfo.split("/from")[1].split("/to")[0].trim();
                String to = bracketInfo.split("/to")[1].trim();
                tasks.add(new Event(description, from, to));
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return;
        }

        System.out.println("Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1)
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.");
    }


    // Print the task list
    private static void printTasks(ArrayList<Task> tasks, int count) {
        // Checks if there is anything in the list
        if (tasks.isEmpty()) {
            System.out.println("☹ OOPS!!! The list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }


    // Mark a task as done
    private static void markTask(String userInput, ArrayList<Task> tasks, int count) {
        // Check if the task list is empty
        if (count == 0) {
            System.out.println("☹ OOPS!!! There are no tasks to mark.");
            return;
        }

        try {
            int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;

            // Check if the task index is valid
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskIndex));
            } else {
                System.out.println("☹ OOPS!!! Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please use 'mark [task number]'.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please use 'mark [task number]'.");
        }
    }


    // Unmark a task
    private static void unmarkTask(String userInput, ArrayList<Task> tasks, int count) {
        // Check if the task list is empty
        if (count == 0) {
            System.out.println("☹ OOPS!!! There are no tasks to unmark.");
            return;
        }

        try {
            int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;

            // Check if the task index is valid
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(taskIndex));
            } else {
                System.out.println("☹ OOPS!!! Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please use 'unmark [task number]'.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please use 'unmark [task number]'.");
        }
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

    private static void deleteTask(String userInput, ArrayList<Task> tasks) {
        // Check if the task list is empty
        if (tasks.isEmpty()) {
            System.out.println("☹ OOPS!!! There are no tasks to delete.");
            return;
        }

        try {
            int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;

            // Check if the task index is valid
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.remove(taskIndex);
                System.out.println("Noted. I've removed this task:\n" + removedTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("☹ OOPS!!! Please provide a valid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please use 'delete [task number]'.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please use 'delete [task number]'.");
        }
    }

    // Load tasks from the data file when the chatbot starts
    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(DATA_FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.fromFileString(line);
                    tasks.add(task);
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    // Save tasks to the data file
    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH));
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(); // Create an instance of the Duke class
        duke.run(); // Run the chatbot
    }
}