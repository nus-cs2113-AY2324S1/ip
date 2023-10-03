package MySun.commands;

import MySun.data.TaskList;
import MySun.data.exception.InvalidActionException;

public abstract class Command {
    public abstract void execute(TaskList tasks) throws InvalidActionException;
}
