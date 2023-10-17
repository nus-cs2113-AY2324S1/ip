package Commands;

import Exceptions.KenException;
import Tasks.TaskList;

/**
 * The abstract base class for all commands in the Barbie-themed task manager.
 * Subclasses of Command represent specific actions that can be performed by the user.
 */
public abstract class Command {

    /**
     * Abstract method to execute the command by performing the specific action on the task list.
     *
     * @param list The task list on which the command should operate.
     * @throws KenException If an error occurs while executing the command.
     */
    public abstract void run(TaskList list) throws KenException;
}
