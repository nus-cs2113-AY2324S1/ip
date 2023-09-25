import commands.Command;
import commands.ExitCommand;
import data.exception.IncompleteDescriptionException;
import data.exception.InvalidActionException;
import data.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the Spaceman application which serves as a personal assistance chatbot.
 * Initializes the application and starts the interaction with the user.
 */
public class Spaceman {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Sets up the required objects and loads up the data from the storage file.
     * @param filePath path of the file used to store data
     */
    public Spaceman (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readDataFromFile());
        } catch (FileNotFoundException e) {
            ui.printReadDataError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command command = Parser.parseCommand(fullCommand, tasks);
                command.execute(tasks);
                isExit = ExitCommand.isExit(command);
                storage.writeToFile(tasks);
            } catch (InvalidActionException e) {
                Ui.showLine();
                System.out.println(e.getMessage());
                Ui.showLine();
            } catch (IncompleteDescriptionException e) {
                Ui.showLine();
                System.out.println(e.getMessage());
                Ui.showLine();
            } catch (IOException e) {
                Ui.showLine();
                System.out.println("Something went wrong: " + e.getMessage());
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Spaceman("./data/spaceman.txt").run();
    }
}