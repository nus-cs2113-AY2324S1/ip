package nupjuk.command;
import nupjuk.TaskList;
public abstract class NupjukCommand {
    public abstract boolean execute(TaskList tasks);
}
