package duke;

import java.util.Scanner;
import java.io.IOException;
import duke.inputProcess.Parser;
import duke.inputProcess.TaskList;
import java.io.FileNotFoundException;
import duke.tasksStorage.SaveToFile;
import duke.tasksStorage.GetFromFile;

/**
 * The `Duke` class is the main class for the command-line task management robot Hilary.
 * It handles user input process, task management, and tasks storage, allow user to
 * add, mark, unmark, delete, and search for tasks. It also supports
 * different types of tasks such as Todo, Deadline, and Event.
 *
 * @author Cheung Ka Yuen
 * @version Final
 * @since 2023-10-24
 */
public class Duke {
    private final GetFromFile getTasks;
    private final SaveToFile saveTasks;
    private final TaskList tasks;
    Scanner in;

    /**
     * Constructs a `Duke` object with the specified file path.
     *
     * @param filePath The file path for storing and retrieving task data.
     */
    private Duke(String filePath) {
        getTasks = new GetFromFile(filePath);
        saveTasks = new SaveToFile(filePath);
        tasks = new TaskList();
        in = new Scanner(System.in);
        Ui.greeting();
    }

    /**
     * The main entry point for the Hilary robot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Duke duke = new Duke("Duke.txt");
        try {
            duke.getTasks.getFromTextFile(duke.tasks);
        } catch (FileNotFoundException e) {
            System.out.println("\tOOPS!!! File not found.");
        }
        String userInput = duke.in.nextLine();
        Parser parser = new Parser(userInput);
        while (!userInput.equals("bye")) {
            String command = userInput;
            if (!userInput.equals("list") && !userInput.equals("help")) {
                try {
                    command = parser.getCommand();
                    userInput = parser.getRemainingPart();
                    parser.newUserInput(userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                    userInput = duke.in.nextLine();
                    parser.newUserInput(userInput);
                    continue;
                }
            }
            new CommandExecutor(duke.tasks).executeCommand(command, userInput, parser);
            try {
                duke.saveTasks.saveToTextFile(duke.tasks);
            } catch (IOException e) {
                System.out.println("Something went wrong while saving the file.");
            }
            userInput = duke.in.nextLine();
            parser.newUserInput(userInput);
        }
        duke.in.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
