package chat0pt;

import chat0pt.commands.Command;
import chat0pt.helper.DukeException;
import chat0pt.tasks.TaskList;
import chat0pt.storage.Storage;
import chat0pt.ui.Ui;
import chat0pt.parser.Parser;

/**
 * Start of the bot Chat0PT
 */
public class Duke {
    private static Ui ui;
    private static TaskList tasks;

    private static Storage storage;

    /**
     * Bot constructor to instantiate the Ui, Storage and TaskList objects, including loading the saved tasks from the file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        tasks = new TaskList(ui, storage.onStart());
    }

    /**
     * Bot would accept input 1 command at a time until the command 'bye' is given.
     */
    public void run() {
        ui.welcomeMessage();
        boolean exitProgram = false;
        while (!exitProgram) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser().parse(fullCommand);
                c.runCommand(ui, tasks);
                exitProgram = c.getProgramStatus();
                storage.writeFile(tasks.returnTaskList());
            } catch (DukeException e) {
                String[] errormsg = {e.getMessage()};
                ui.print(errormsg);
            }
        }
        ui.goodbyeMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
