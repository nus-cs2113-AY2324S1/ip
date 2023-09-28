package duke;

import duke.command.Command;
import duke.exception.DukeCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;
import duke.parser.Parser;

public class Duke {

    private TextUi ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private void setup() {
        ui = new TextUi();
        storage = new Storage();
        tasks = new TaskList(storage.restoreSavedData());
        parser = new Parser();
    }

    private void run() {
        String input;
        boolean isExit = false;

        ui.tellGreeting();
        while (!isExit) {
            input = ui.getInput();
            try {
                ui.showLine();
                Command command = parser.parseCommand(input);
                command.executeCommand(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeCommandException exception) {
                exception.handleDukeCommandException(parser.parseCommandType(input)[0]);
            } finally {
                ui.showLine();
            }
        }
    }

    private void start() {
        setup();
        run();
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}