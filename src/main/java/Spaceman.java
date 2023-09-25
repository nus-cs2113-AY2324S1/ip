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
                isExit = Parser.inputValidation(fullCommand, tasks);
                storage.writeToFile(tasks);
            } catch (InvalidActionException e) {
                ui.showLine();
                System.out.println(e.getMessage());
                ui.showLine();
            } catch (IncompleteDescriptionException e) {
                ui.showLine();
                System.out.println(e.getMessage());
                ui.showLine();
            } catch (IOException e) {
                ui.showLine();
                System.out.println("Something went wrong: " + e.getMessage());
                ui.showLine();
            }
        }
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Spaceman("./data/spaceman.txt").run();
    }
}