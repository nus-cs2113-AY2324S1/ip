package doli;

import doli.GUI.Ui;
import doli.commands.Command;
import doli.commands.Parser;
import doli.exceptions.DoliExceptions;
import doli.files.Storage;
import doli.tasks.TaskList;

import java.io.IOException;

public class Doli {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Doli(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.initializeFile());
        } catch (DoliExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getInput();
                ui.printLine();
                Command c = Parser.parseInputIntoCommand(fullCommand);
                c.handleCommand(tasks, ui, storage);
                c.getResponse();
                isExit = c.isExit();
            } catch (DoliExceptions e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }
    public static void main(String[] args) {
        new Doli("Agenda.txt").run();
    }
}
