package Command;

import Soccat.Todo;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String INVALID_PROMPT = "Oops, Todo task cannot be empty!";

    private final Todo todoTask;

    public TodoCommand(String taskName) {
        this.todoTask = new Todo(taskName);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        try {
            tasks.addTask(todoTask, taskFile);
            ui.displayAddedTask(todoTask, tasks, COMMAND_WORD);
        } catch (IOException e) {
            System.out.println("IO Exception occurred during file storage!");
        }
        return false;
    }
}
