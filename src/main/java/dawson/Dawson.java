package dawson;

import dawson.command.Command;
import dawson.command.CommandResult;
import dawson.command.ExitCommand;
import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.storage.Storage;
import dawson.task.TaskList;
import dawson.ui.TextUI;

public class Dawson {

    private Command newCommand;
    private TextUI ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Dawson constructor: Mandatory initialisation of ui and storage objects 
     * before calling setup or run methods
     */
    private Dawson() {
        ui = new TextUI();
        storage = new Storage("data/dawson.txt");

        try {
            taskList = new TaskList(storage.load());
        } catch (DawsonException e) {
            ui.printText(e.getMessage());
            taskList = new TaskList();
        }
    }

    private void setup() {
        ui.printWelcomeText();
    }

    private void run() {
        do {
            String nextLineString = ui.getUserCommand();
            newCommand = Parser.parseCommand(nextLineString, taskList);

            try {
                CommandResult result = newCommand.execute();
                ui.printCommandResult(result);
                storage.save(taskList);
                
            } catch (DawsonException e) {
                ui.printText(e.getMessage());
            }
        } while (!(newCommand instanceof ExitCommand));

        System.exit(0);
    }

    public static void main(String[] args) {
        Dawson dawson = new Dawson();
        dawson.setup();
        dawson.run();
    }
}
