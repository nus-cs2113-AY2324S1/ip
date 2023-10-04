package AMY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

import AMY.Exceptions.EmptyDeadlineException;
import AMY.Exceptions.EmptyEventException;
import AMY.Exceptions.EmptyInput;
import AMY.Exceptions.EmptyMarkException;
import AMY.Exceptions.EmptyToDoException;
import AMY.Exceptions.EmptyUnmarkException;

import AMY.command.Deadline;
import AMY.command.Event;
import AMY.command.Task;
import AMY.command.Todo;
public class AMY {
    public static final String BOT_NAME = "AMY";
    public static final String LINE = "____________________________________________________________";
    public static Task[] taskList = new Task[100];
    public static int numberOfTasks = 0;

    // Draws a line
    public static void drawLine() {
        System.out.println(LINE);
    }

    // Writes a welcome message
    public static void welcomeMessage() {
        drawLine();
        String logo = "        \n"
                + "    /\\    |\\  /| \\   / \n"
                + "   /  \\   | \\/ |  \\ /  \n"
                + "  /----\\  |    |   |   \n"
                + " /      \\ |    |   |   \n";
        System.out.println("Hello from\n" + logo);

        drawLine();
        System.out.println("Hello! I'm " + BOT_NAME);
        System.out.println("What can I do for you?");
        drawLine();
    }

    // Writes a bye message
    public static void byMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
    }

    // File path for storing tasks
    private static final String FILE_PATH = "./data/AMY.txt";

    // Save tasks to a file
    public static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (int i = 0; i < numberOfTasks; i++) {
                Task task = taskList[i];
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Error saving tasks to the file.");
        }
    }

    // Load tasks from a file
    public static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            int numberOfTasks = 0; // Initialize numberOfTasks
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = Task.parseFromFileString(taskData);
                if (task != null) {
                    AMY.addToList(task);
                    numberOfTasks++; // Increment numberOfTasks
                }
            }
            scanner.close();
            AMY.numberOfTasks = numberOfTasks; // Update numberOfTasks in AMY class
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new file.");
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Error loading tasks from the file.");
        }
    }

    // Add a task to the list
    public static void addToList(Task task) {
        taskList[numberOfTasks] = task;
        numberOfTasks++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list");
    }

    // List all tasks if the user enters "list"
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }

    // Mark task as done if the user enters "mark"
    public static void markTaskAsDone(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= numberOfTasks) {
            Task task = taskList[taskIndex - 1];
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    // Unmark task as done if the user enters "unmark"
    public static void unmarkTask(int taskIndex) {
        if (taskIndex >= 1 && taskIndex <= numberOfTasks) {
            Task task = taskList[taskIndex - 1];
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    public static void manageException(String userInput) throws EmptyInput, EmptyToDoException,
            EmptyMarkException, EmptyUnmarkException, EmptyDeadlineException, EmptyEventException {

        Scanner input = new Scanner(userInput);
        String command;
        if (!input.hasNext()) {
            throw new EmptyInput();
        } else {
            command = input.next();
        }
        if (command.equals("todo") && !input.hasNext()) {
            throw new EmptyToDoException();
        }
        if (command.equals("mark") && !input.hasNext()) {
            throw new EmptyMarkException();
        }
        if (command.equals("unmark") && !input.hasNext()) {
            throw new EmptyUnmarkException();
        }
        if (command.equals("deadline") && !input.hasNext()) {
            throw new EmptyDeadlineException();
        }
        if (command.equals("event") && !input.hasNext()) {
            throw new EmptyEventException();
        }
    }

    public static void manageInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine(); // Read user input
            System.out.println(LINE);
            try {
                manageException(userInput);
                if (userInput.equals("bye")) {
                    break; // Exit the loop if the user enters "bye"
                } else if (userInput.equals("list")) {
                    listTasks(); // list tasks
                } else if (userInput.startsWith("mark")) {
                    // Extract the task index from the user input
                    int taskIndex = Integer.parseInt(userInput.substring(5).trim());
                    markTaskAsDone(taskIndex);
                } else if (userInput.startsWith("unmark")) {
                    // Extract the task index from the user input
                    int taskIndex = Integer.parseInt(userInput.substring(7).trim());
                    unmarkTask(taskIndex);
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(5).trim();
                    Todo todo = new Todo(description);
                    addToList(todo);
                } else if (userInput.startsWith("deadline")) {
                    int inputIndex = userInput.indexOf(" /by ");
                    String description = userInput.substring("deadline ".length(), inputIndex);
                    String by = userInput.substring(inputIndex + " /by ".length());
                    addToList(new Deadline(description, by));
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.substring(5).split("/from");
                    String description = parts[0].trim();
                    String[] dateTimeParts = parts[1].split("/to");
                    String from = dateTimeParts[0].trim();
                    String to = dateTimeParts[1].trim();
                    Event event = new Event(description, from, to);
                    addToList(event);
                } else {
                    System.out.println("Invalid command. Please try again.");
                }
            } catch (EmptyInput exception) {
                System.out.println("☹ OOPS!!! The description cannot be empty.");
            } catch (EmptyToDoException exception) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } catch (EmptyMarkException exception) {
                System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
            } catch (EmptyUnmarkException exception) {
                System.out.println("☹ OOPS!!! The description of an unmark cannot be empty.");
            } catch (EmptyDeadlineException exception) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch (EmptyEventException exception) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            }
            drawLine();
        }
    }

    // Main method executes the Chat bot
    public static void main(String[] args) {
        welcomeMessage();
        loadTasksFromFile();
        manageInput();
        saveTasksToFile();
        byMessage();
    }
}
