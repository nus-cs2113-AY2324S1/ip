package MySun.commands;

import MySun.data.TaskList;
import MySun.data.exception.InvalidActionException;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks) throws InvalidActionException;
}
