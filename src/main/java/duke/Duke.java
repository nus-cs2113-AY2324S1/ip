package duke;

import duke.command.DukeException;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class Duke {
    private TextUi ui;
    private Parser parser;
    private TaskList tasks;
    private StorageFile storage;

    public Duke(String filePath) {
        tasks = new TaskList();
        ui = new TextUi();
        parser = new Parser();
        storage = new StorageFile(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showInitFailedMessage();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();

        String userCommandText = ui.getUserCommand();
        while (!userCommandText.equals("bye")) {
            String result = handleCommand(userCommandText);
            ui.showResultToUser(result);
            userCommandText = ui.getUserCommand();
        }

        ui.showGoodbyeMessage();
    }

    public String handleCommand(String userInput) {
        if (userInput.equals("list")) {
            return tasks.getIndexedTasks();
        } else {
            try {
                String result = parser.executeCommand(userInput, tasks);
                storage.saveTasks(tasks);
                return result;
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
