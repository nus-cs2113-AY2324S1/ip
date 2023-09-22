package commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.OutOfRangeException;
import exceptions.TaskEmptyDescriptionException;

/**
 * Represents a command that can be executed by the user.
 */
public abstract class Command {
    
    public abstract void execute() throws TaskEmptyDescriptionException, OutOfRangeException, FileNotFoundException, IOException;
}
