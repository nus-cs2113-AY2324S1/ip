package Command;

import Storage.TaskList;
import Ui.Ui;
import Storage.Storage;

public abstract class Command {
    public static final String INVALID_PROMPT = "Oops, your command is not recognized, please try again!";
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);
}
