package Command;

import Storage.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks);
}
