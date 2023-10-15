package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasks.Tasklist;
import duke.storage.FileRW;
import duke.ui.Ui;

/**
 * The main class for Duke.
 */
public class Duke {

    /**
     * The main method for Duke.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Create a new Tasklist object to store the tasks
            Tasklist tasks = new Tasklist();
            // Read the tasks from the file and add them to the Tasklist object
            FileRW.readFromFile(tasks);

            // Show the welcome message to the user
            Ui.showWelcome();

            // Create a new Command object to store the next command to be executed
            Command nextCommand = new Command();

            // Keep looping until the user enters the "bye" command
            while (!nextCommand.isExit()) {
                try {
                    // Read the user's input and parse it into a Command object
                    String input = Ui.readCommand();
                    nextCommand = Parser.parse(input);
                    // Execute the Command object on the Tasklist object
                    nextCommand.execute(tasks);
                } catch (DukeException e) {
                    // If there is an error, show the error message to the user
                    Ui.showMessage(e.getMessage());
                }
            }
            
            // Show the goodbye message to the user
            Ui.showGoodbye();

            // Write the tasks to the file
            FileRW.writeToFile(tasks);
        } catch (DukeException e) {
            // If there is an error, show the error message to the user
            Ui.showMessage(e.getMessage());
        }
    }
}