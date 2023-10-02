package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


import duke.DeadlineTask;
import duke.EventTask;
import duke.DukeException;
import duke.TasksHandler;
import duke.TodoTask;
import duke.Ui;
import duke.TaskList;
import duke.Storage;
//import the rest

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;


    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks.getTasks(), ui, storage);
                isExit = c.isExit();
                Storage.saveTasks(tasks.getTasks());
            } catch (IOException | DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/tasks.txt").run();
    }


}