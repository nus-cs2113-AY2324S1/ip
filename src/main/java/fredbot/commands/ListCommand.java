package fredbot.commands;

import fredbot.Storage;
import fredbot.TaskList;
import fredbot.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printAllTasks(tasks);
    }
}
