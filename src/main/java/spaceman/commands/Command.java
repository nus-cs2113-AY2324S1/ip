package spaceman.commands;

import spaceman.data.TaskList;
import spaceman.data.exception.InvalidActionException;

public abstract class Command {
    public abstract void execute(TaskList tasks) throws InvalidActionException;
}
