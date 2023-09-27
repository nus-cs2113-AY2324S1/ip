package spaceman.commands;

import static spaceman.ui.Messages.MESSAGE_UNKNOWN;

import spaceman.data.TaskList;
import spaceman.data.exception.InvalidActionException;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws InvalidActionException {
        throw new InvalidActionException(MESSAGE_UNKNOWN);
    }
}
