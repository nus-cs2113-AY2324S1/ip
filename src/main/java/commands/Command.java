package commands;

import data.TaskList;
import data.exception.InvalidActionException;

public abstract class Command {
    public abstract void execute(TaskList tasks) throws InvalidActionException;
}
