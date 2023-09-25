package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        setExit(true);
    }
}
