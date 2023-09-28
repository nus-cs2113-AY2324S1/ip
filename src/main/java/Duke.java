import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

/**
 * The Duke class is a simple task management application.
 * It allows users to add, list, mark, find and delete tasks.
 * The tasks are saved in a text file after exiting.
 */

public class Duke {
    private static final String DATA_DIRECTORY = "./data/";
    private static final String DATA_FILE_PATH = DATA_DIRECTORY + "duke.txt";
    private Storage storage; // Create an instance of the Storage class
    private Ui ui; // Create an instance of the Ui class
    private CommandHandler commandHandler; // Create an instance of the CommandHandler class

    /**
     * Constructor for Duke class. Initialises storage, ui and commandHandler.
     */
    public Duke() {
        this.storage = new Storage(DATA_FILE_PATH);
        this.ui = new Ui();
        this.commandHandler = new CommandHandler();
    }

    /**
     * The main method to start the duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke application, loading tasks, processing user inputs and saving tasks.
     */
    private void run() {
        ui.printWelcomeMessage();

        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        List<Task> tasks = storage.loadTasks(); // Load tasks using Storage

        Scanner scanner = new Scanner(System.in);
        String userInput;

        startDuke(scanner, tasks);

        storage.saveTasks(tasks); // Save tasks using Storage
    }

    /**
     * Starts the Duke application, processes user input and handles commands.
     *
     * @param scanner The scanner to read user input.
     * @param tasks   The list of tasks to manage.
     */
    private void startDuke(Scanner scanner, List<Task> tasks) {
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
            commandHandler.handleCommand(userInput, tasks);
        }
    }

    /**
     * Prints a line of equal signs to separate sections of output.
     */
    public static void printLines() {
        for (int i = 0; i < 30; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}

