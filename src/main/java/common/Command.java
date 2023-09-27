package common;

import listWhisper.task.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);

    public boolean isExit() {
        return false;
    }
}
