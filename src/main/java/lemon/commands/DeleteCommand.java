package lemon.commands;

import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;
import lemon.validation.Parser;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int taskIndex;
    private final Parser parser;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        this.parser = new Parser();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws LemonException {
        parser.checkTaskNumberRange(taskIndex, tasks);
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.displayDeletedTask(deletedTask, tasks);
        storage.save(tasks.getTasks());
    }
}
