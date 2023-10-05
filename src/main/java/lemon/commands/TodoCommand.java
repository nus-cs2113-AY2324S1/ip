package lemon.commands;

import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.task.Todo;
import lemon.ui.UI;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Todo todo = new Todo(description, false);
        tasks.addTask(todo);
        ui.displayAddedTask(todo, tasks);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
