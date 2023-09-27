package elgin;

import elgin.command.Command;
import elgin.exception.DukeException;
import elgin.parser.Parser;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.getSavedTasks());
        } catch (IOException | DukeException e) {
            ui.formatPrint(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.sayGreeting();
        String userInput;
        boolean isContinue = true;
        while (isContinue) {
            userInput = scanner.nextLine();
            try {
                Command command = new Parser().parseCommand(userInput);
                command.execute(tasks, ui, storage);
                storage.saveToFile(tasks.getAllTasks());
                isContinue = command.getIsContinue();
                if (!isContinue) {
                    ui.sayBye();
                }
            } catch (DukeException e) {
                ui.formatPrint(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}