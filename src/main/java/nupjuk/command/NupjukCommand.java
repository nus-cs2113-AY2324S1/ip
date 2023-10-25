package nupjuk.command;
import nupjuk.TaskList;

/**
 * Nupjuk Command class
 * abstraction of commands
 */
public abstract class NupjukCommand {
    public abstract boolean execute(TaskList tasks);
}
