package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.printTasks();
    }
}
