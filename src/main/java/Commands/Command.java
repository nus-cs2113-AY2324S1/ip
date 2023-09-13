package commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.OutOfRangeException;
import exceptions.TaskEmptyDescriptionException;

public abstract class Command {
    
    public abstract void execute() throws TaskEmptyDescriptionException, OutOfRangeException, FileNotFoundException, IOException;
}
