package alice;

import alice.exceptions.AliceException;
import alice.parser.CommandParser;
import alice.tasks.*;
import alice.storage.FileManager;
import alice.ui.Ui;

import java.io.*;

public class Alice {
    private static final String LINE = "\n____________________________________________________________\n";
    private FileManager file;
    private TaskList tasks;
    private Ui ui;

    public Alice() {
        ui = new Ui();
        file = new FileManager();
        try {
            this.tasks = new TaskList(file.retrieve());
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.printHelloMessage();
        try {
            CommandParser parser = new CommandParser(tasks);
            parser.execute(file);
            file.save(tasks);
        } catch (AliceException e) {
            ui.printError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Alice().run();
    }


}