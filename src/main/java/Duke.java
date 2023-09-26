import rene.command.Command;
import rene.exception.ReneExceptions;
import rene.parser.Parser;
import rene.tasklist.TaskList;
import rene.ui.Ui;
import rene.storage.Storage;

import java.net.PasswordAuthentication;

public class Duke {
    private Ui ui;
    private Storage dataStorage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath){
        dataStorage = new Storage(filePath);
        tasks = new TaskList();
        ui = new Ui(dataStorage, tasks);
        parser = new Parser();
    }

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
        ui.displayCLosingMessage();
    }

    public static void main(String[] args) {
         new Duke("tasklist.txt").run();
    }
}
