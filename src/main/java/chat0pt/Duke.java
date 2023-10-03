package chat0pt;

import chat0pt.commands.Command;
import chat0pt.helper.DukeException;
import chat0pt.tasks.TaskList;
import chat0pt.storage.Storage;
import chat0pt.ui.Ui;
import chat0pt.parser.Parser;

import java.io.IOException;

public class Duke {
    private static Ui ui;
    private static TaskList tasks;

    private static Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        tasks = new TaskList(ui, storage.onStart());
    }

    public void run() {
        ui.welcomeMessage();
        boolean exitProgram = false;
        while (!exitProgram) {
            try {
                String fullCommand = ui.readCommand();
                Command c = new Parser().parse(fullCommand);
                c.runCommand(ui, storage, tasks);
                exitProgram = c.getProgramStatus();
                storage.writeFile(tasks.returnTaskList());
            } catch (DukeException e) {
                String[] errormsg = {e.getMessage()};
                ui.print(errormsg);
            }
        }
        ui.goodbyeMessage();
    }

    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.run();
    }
}
