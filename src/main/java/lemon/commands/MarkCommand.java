package lemon.commands;

import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.Task;
import lemon.task.TaskList;
import lemon.ui.UI;
import lemon.validation.Parser;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private final int taskIndex;
    private final Parser parser;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
        this.parser = new Parser();
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws LemonException {
        parser.checkTaskNumberRange(taskIndex, tasks);
        Task markedTask = tasks.markTask(taskIndex);
        ui.displayMarkedTask(markedTask);
        storage.save(tasks.getTasks());
    }
}
