package duke;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.Parser;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        tasks.setTasks(storage.loadTasks());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand, ui);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("./tasks.txt").run();
    }
}
