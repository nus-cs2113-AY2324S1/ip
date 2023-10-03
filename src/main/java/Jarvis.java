import jarvis.command.Command;
import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.tasklist.TaskList;
import jarvis.storage.Storage;
import jarvis.ui.Ui;

import java.io.IOException;

public class Jarvis {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage dataStorage;
    private final Parser parser;
    static String filePath = "tasklist.txt";
    /**
     * Constructs a new instance of Jarvis.
     * Initializes the task list, data storage, user interface, and command parser.
     * @param filePath The path to the file where task data is stored.
     */
    public Jarvis(String filePath){
        taskList = new TaskList();
        dataStorage = new Storage(filePath);
        ui = new Ui(dataStorage, taskList);
        parser = new Parser();
    }
    /**
     * Runs the Jarvis application.
     * Manages the main execution loop where user commands are read, parsed, and executed.
     * @throws JarvisException if any error occurs related to Jarvis-specific operations.
     * @throws IOException if any I/O error occurs.
     */
    public void runJarvis() throws JarvisException, IOException {
        ui.displayWelcomeMessage();
        boolean toExit = false;
        while(!toExit){
            String userCommand = ui.readCommand();
            Command command = parser.parseCommand(userCommand);
            toExit = command.toExit();
            command.executeCommand(ui, dataStorage, taskList);
            ui.displayLine();
        }
        ui.displayGoodbyeMessage();
    }
    /**
     * The entry point of the Jarvis application.
     * Initializes a new Jarvis instance and runs it.
     * @param args Command-line arguments. Not utilized in this application.
     * @throws JarvisException if any error occurs related to Jarvis-specific operations.
     * @throws IOException if any I/O error occurs.
     */
    public static void main(String[] args) throws JarvisException, IOException {
        new Jarvis(filePath).runJarvis();
    }
}
