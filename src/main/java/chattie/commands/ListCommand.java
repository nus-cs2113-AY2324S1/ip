package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.Storage;
import chattie.tasks.Task;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        ArrayList<Task> list = tasks.getList();

        ui.printTaskList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
