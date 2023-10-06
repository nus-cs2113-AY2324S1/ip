import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ken initializes its components, including the user interface, storage for task data, and the task list.
 * It loads tasks from a file upon startup and
 * provides a run loop to process user commands until the "bye" command is entered.
 */
public class Ken {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a Ken object with the specified file path.
     *
     * @param filePath The path to the file where task data is stored.
     */
    public Ken(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();

        // Load tasks from the file when the chatbot starts up
        storage.loadTasks(taskList);
    }

    /**
     * The main method to start the Ken chatbot.
     *
     * @throws KenException If there is an exception during chatbot initialization or execution.
     */
    public static void main(String[] args) throws KenException {
        Ken ken = new Ken("ken.txt");
        ken.run();
    }

    /**
     * Starts the chatbot's main run loop. It continuously prompts the user for input and
     * processes commands until the "bye" command is entered.
     *
     * @throws KenException If there is an exception during command processing.
     */
    public void run() throws KenException {
        Ui.printLine();
        Ui.printWelcomeMessage();
        Ui.printLine();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            Ui.printLine();

            if (userInput.toLowerCase().startsWith(CommandParser.COMMAND_FIND)) {
                taskList.handleFindCommand(userInput);
            } else if (userInput.equalsIgnoreCase(CommandParser.COMMAND_BYE)) {
                Ui.printGoodbyeMessage();
                break;
            } else {
                try {
                    CommandParser.processUserCommand(userInput, taskList, storage);
                } catch (KenException e) {
                    if (userInput.equalsIgnoreCase(CommandParser.COMMAND_LIST)) {
                        TaskList.listTasks(taskList);
                    } else {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
            Ui.printLine();
        }

        scanner.close();
    }
}


class KenException extends Exception {
    public KenException(String message) {
        super(message);
    }
}


class InvalidCommandException extends KenException {
    public InvalidCommandException(String message) {
        super(message);
    }
}

class EmptyDescriptionException extends KenException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

class TaskNotFoundException extends KenException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}





