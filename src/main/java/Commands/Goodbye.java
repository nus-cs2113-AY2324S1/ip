package Commands;

import Exceptions.KenException;
import Tasks.TaskList;
import UI.Ui;

import static Storage.Storage.writeToFile;

/**
 * Represents a command to exit the Barbie-themed task manager gracefully.
 */
public class Goodbye extends Command {
    /**
     * Executes the "Goodbye" command, bidding farewell to the user and saving the current TaskList to a file.
     *
     * @param list The task list on which the command should operate.
     * @throws KenException If an error occurs while writing the TaskList to a file.
     */
    @Override
    public void run(TaskList list) throws KenException {
        Ui.byeUser();
        writeToFile(list);
    }
}
