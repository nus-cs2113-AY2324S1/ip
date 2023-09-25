package Commands;

import Exceptions.KenException;
import Tasks.TaskList;

public abstract class Command {
    public abstract void run(TaskList list) throws KenException;
}
