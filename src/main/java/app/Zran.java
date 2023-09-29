package app;

import commands.Commands;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Parser;
import userinputs.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Zran {

    private final Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Zran(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            Commands c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExitCommand(fullCommand);
        }

        storage.saveTasks(tasks);
        ui.showGoodbye();
    }

    public static void main(String[] args) throws IOException {
        new Zran("./data/tasks.txt").run();
    }
}

