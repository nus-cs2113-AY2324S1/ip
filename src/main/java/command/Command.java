package command;

import task.TaskList;
import ui.Ui;

public abstract class Command {

    //execute itself
    public abstract void execute(TaskList taskList, Ui ui);

    //check whether this is an exit command
    public boolean isExit() {
        return false;
    }
}
