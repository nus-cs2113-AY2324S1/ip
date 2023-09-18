package Commands;

import Exceptions.KenException;
import Ken.TaskList;

public abstract class Command {
    public abstract void run(TaskList list) throws KenException;
}
