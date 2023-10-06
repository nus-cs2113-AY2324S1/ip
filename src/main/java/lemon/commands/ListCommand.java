package lemon.commands;

import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.displayEmptyList();
        } else {
            ArrayList<Task> taskList = tasks.getTasks();
            ui.displayTaskList(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
