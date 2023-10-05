package lemon.commands;

import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;
import lemon.validation.Parser;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final int taskIndex;
    private final Parser parser;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        this.parser = new Parser();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws LemonException {
        parser.checkTaskNumberRange(taskIndex, tasks);
        Task unmarkedTask = tasks.unmarkTask(taskIndex);
        ui.displayUnmarkedTask(unmarkedTask);
        storage.save(tasks.getTasks());
    }
}
