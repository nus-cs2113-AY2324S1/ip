package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;

public abstract class Command {


    public abstract void execute(TaskList tasks, Ui ui) throws ChattieException;

    public abstract boolean isExit();
}
