package MySun;

import MySun.commands.Command;
import MySun.commands.ExitCommand;
import MySun.data.TaskList;
import MySun.data.exception.SunException;
import MySun.ui.Ui;
import MySun.storage.Storage;
import MySun.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * The MySun class represents the main application that manages tasks. It handles user interactions
 * through a command-line interface, parses user commands, and stores tasks in a data file.
 */
public class MySun {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new MySun instance with the specified file path.
     * @param filePath The path to the data file where tasks are stored.
     * @throws IOException If an I/O error occurs while creating or accessing the data file.
     */
    public MySun(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readDataFromFile());
        } catch (FileNotFoundException e) {
            Ui.showReadDataError();
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            tasks = new TaskList(storage.readDataFromFile());
        }
    }

    /**
     * Runs the MySun application, displaying a welcome message, processing user commands,
     * and managing tasks until the user chooses to exit.
     */
    private void run() {
        Ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command command = Parser.parseCommand(fullCommand, tasks);
                command.execute(tasks);
                isExit = ExitCommand.isExit(command);
                storage.writeToFile(tasks);
            } catch (SunException | IndexOutOfBoundsException | ParseException e) {
                ui.showException(e);
            } catch (IOException e) {
                Ui.showLine();
                System.out.println("Something went wrong: " + e.getMessage());
                Ui.showLine();
            }
        }
    }

    /**
     * The main method to start the MySun application.
     * @param args The command-line arguments (not used in this application).
     * @throws IOException If an I/O error occurs while initializing the application.
     */
    public static void main(String[] args) throws IOException {
        new MySun("./data/MySun.txt").run();
    }
}

