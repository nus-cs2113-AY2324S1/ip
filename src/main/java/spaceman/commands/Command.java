package spaceman.commands;

import spaceman.data.TaskList;
import spaceman.data.exception.InvalidActionException;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     */
    public abstract void execute(TaskList tasks) throws InvalidActionException;
}
