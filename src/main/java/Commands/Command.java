package Commands;

import Data.TaskList;
import Exceptions.CSGPTException;
import Ui.Ui;

/**
 * Abstract class for all commands
 */
public abstract class Command {

    /**
     * Abstract method to execute the command
     * @param tasklist Tasklist to be modified
     * @param ui Ui to print messages
     * @throws CSGPTException Exception thrown when there is an error
     */
    public abstract void execute(TaskList tasklist, Ui ui) throws CSGPTException;
}
