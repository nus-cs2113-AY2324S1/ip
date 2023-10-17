package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;

import java.io.IOException;

/**
 * Represents a generic Command that has a generic execute function and a boolean to represent whether to
 * exit FredBot
 */
public abstract class Command {
    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {}
}
