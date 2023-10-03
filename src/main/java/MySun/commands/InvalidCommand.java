package MySun.commands;

import MySun.data.TaskList;
import MySun.data.exception.InvalidActionException;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws InvalidActionException {
        throw new InvalidActionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
