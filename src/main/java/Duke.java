import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Duke {
    private static final String DATA_DIRECTORY = "./data/";
    private static final String DATA_FILE_PATH = DATA_DIRECTORY + "duke.txt";
    private Storage storage;
    private Ui ui;
    private CommandHandler commandHandler;

    public Duke() {
        this.storage = new Storage(DATA_FILE_PATH);
        this.ui = new Ui();
        this.commandHandler = new CommandHandler();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui.printWelcomeMessage();

        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        List<Task> tasks = storage.loadTasks();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        startDuke(scanner, tasks);

        storage.saveTasks(tasks);
    }

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

    public static void printLines() {
        for (int i = 0; i < 30; i++) {
            System.out.print('=');
        }
        System.out.println();
    }
}

