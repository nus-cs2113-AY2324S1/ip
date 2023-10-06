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

    /**
     * Retrieves tasks in text file and store it in a TaskList
     */
    public Alice() {
        file = new FileManager();
        try {
            this.tasks = new TaskList(file.retrieve());
        } catch (FileNotFoundException e) {
            Ui.printLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the program and saves new content into the text file
     */
    public void run() {
        Ui.printHelloMessage();
        try {
            CommandParser parser = new CommandParser(tasks);
            parser.execute(file);
            file.save(tasks);
        } catch (AliceException e) {
            Ui.printError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Alice().run();
    }


}