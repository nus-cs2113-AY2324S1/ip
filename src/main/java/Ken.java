import java.util.Scanner;

public class Ken {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Ken(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();

        // Load tasks from the file when the chatbot starts up
        storage.loadTasks(taskList);
    }

    public void run() {
        Ui.printLine();
        Ui.printWelcomeMessage();
        Ui.printLine();

        Scanner scanner = new Scanner(System.in);

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

    public static void main(String[] args) {
        new Ken("ken.txt").run();
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



