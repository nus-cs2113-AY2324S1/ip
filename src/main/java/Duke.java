import rene.command.Command;
import rene.parser.Parser;
import rene.tasklist.TaskList;
import rene.ui.Ui;
import rene.storage.Storage;

/**
 * The main program running for the Duke chatbot.
 * The program bridges the user interface (Ui),
 * hard disk data (dataStorage) and supports
 * dynamic data manipulation.
 */
public class Duke {
    private Ui ui;
    private Storage dataStorage;
    private TaskList tasks;
    private Parser parser;
    /**
     * Initiates the program with the necessary components.
     *
     * @param filePath File location where data is
     *                 written to and read from by the program.
     */
    public Duke(String filePath){
        dataStorage = new Storage(filePath);
        tasks = new TaskList();
        ui = new Ui(dataStorage, tasks);
        parser = new Parser();
    }
    /**
     * Starts the chatbot by showing opening message.
     * Chatbot repeatedly handles user commands
     * until it is requested to terminate.
     * Chatbot closes by showing closing message.
     */
    public void run(){
        ui.displayOpeningMessage();
        boolean toExit = false;
        while(!toExit){
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command command = parser.parseCommand(fullCommand);
            toExit = command.toExit();
            command.executeCommand(ui, dataStorage, tasks);
            if(!toExit){
                ui.showLine();
            }
        }
        ui.displayClosingMessage();
    }

    public static void main(String[] args) {
         new Duke("tasklist.txt").run();
    }
}
