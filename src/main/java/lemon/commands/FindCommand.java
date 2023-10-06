package lemon.commands;

import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;

import java.util.ArrayList;

import static lemon.common.Messages.MESSAGE_FOUND_TASK;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ArrayList<Task> matchedTasks = tasks.findTask(keyword);
        if (matchedTasks.isEmpty()) {
            ui.displayUnfoundTask();
        } else {
            ui.displayFoundList(matchedTasks);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
