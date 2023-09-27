package elgin.command;

import elgin.exception.DukeException;
import elgin.storage.Storage;
import elgin.task.TaskList;
import elgin.ui.Ui;

public abstract class Command {

    public boolean isContinue = true;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean getIsContinue() {
        return isContinue;
    }
}
