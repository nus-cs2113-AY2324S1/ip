package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;

/**
 * Represents a command to be executed by Duke.
 */
public class Command {

    /**
     * Constructs a new Command object.
     */
    public Command() {
    }

    /**
     * Executes the Command by modifying the given Tasklist.
     *
     * @param tasks The Tasklist object to modify.
     * @throws DukeException If there is an error executing the command.
     */
    public void execute(Tasklist tasks) throws DukeException {
    }

    /**
     * Returns whether the Command is an exit command.
     *
     * @return True if the Command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    
}