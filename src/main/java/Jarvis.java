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
    public Jarvis(String filePath){
        taskList = new TaskList();
        dataStorage = new Storage(filePath);
        ui = new Ui(dataStorage, taskList);
        parser = new Parser();
    }

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

    public static void main(String[] args) throws JarvisException, IOException {
        new Jarvis(filePath).runJarvis();
    }
}
