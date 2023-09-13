package commands;

import exceptions.OutOfRangeException;
import exceptions.TaskEmptyDescriptionException;

public abstract class Command {
    
    public abstract void execute() throws TaskEmptyDescriptionException, OutOfRangeException;
}
