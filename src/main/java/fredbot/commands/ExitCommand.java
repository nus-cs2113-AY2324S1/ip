package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;

/**
 * Represent a class for executing exit task command
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        setExit(true);
    }
}
