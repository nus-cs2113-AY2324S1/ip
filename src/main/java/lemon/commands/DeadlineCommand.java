package lemon.commands;

import lemon.file.Storage;
import lemon.task.Deadline;
import lemon.task.TaskList;
import lemon.ui.UI;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Deadline deadline = new Deadline(description, by, false);
        tasks.addTask(deadline);
        ui.displayAddedTask(deadline, tasks);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
