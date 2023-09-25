package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;

public abstract class Command {
    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public void execute(TaskList tasks, Storage storage) {}
}
