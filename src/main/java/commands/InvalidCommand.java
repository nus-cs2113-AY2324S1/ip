package commands;

import static ui.Messages.MESSAGE_UNKNOWN;

import data.TaskList;
import data.exception.InvalidActionException;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws InvalidActionException {
        throw new InvalidActionException(MESSAGE_UNKNOWN);
    }
}
