package Commands;

import Ken.TaskList;

public abstract class Command {
    public abstract void run(TaskList list);
}
