package Commands;

import Exceptions.OutOfRangeException;
import Exceptions.TaskEmptyDescriptionException;

public abstract class Command {
    
    public abstract void execute() throws TaskEmptyDescriptionException, OutOfRangeException;
}
