import java.util.Scanner;

public class Ken {
    private static final String DATA_FILE_PATH = "ken.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage(DATA_FILE_PATH);
        TaskList taskList = new TaskList(); // Create a new TaskList instance

        // Load tasks from the file when the chatbot starts up
        storage.loadTasks(taskList);

        Ui.printLine();
        Ui.printWelcomeMessage();
        Ui.printLine();

        while (true) {
            String userInput = scanner.nextLine();
            Ui.printLine();

            if (userInput.equalsIgnoreCase(CommandParser.COMMAND_BYE)) {
                Ui.printGoodbyeMessage();
                break;
            } else {
                try {
                    CommandParser.processUserCommand(userInput, taskList, storage);
                } catch (KenException e) {
                    if (userInput.equalsIgnoreCase(CommandParser.COMMAND_LIST)) {
                        TaskList.listTasks(taskList); // Call the listTasks method
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



