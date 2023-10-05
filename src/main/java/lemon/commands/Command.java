package lemon.commands;

import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;

public class Command {
    public boolean isExit;

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws LemonException {
    }

}
