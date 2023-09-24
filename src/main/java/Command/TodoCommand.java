package Command;

import Soccat.Todo;
import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a todo.
 * A <code>TodoCommand</code> corresponds to a command
 * consisting of a task only.
 * */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String INVALID_PROMPT = "Oops, Todo task cannot be empty!";

    private final Todo todoTask;

    public TodoCommand(String taskName) {
        this.todoTask = new Todo(taskName);
    }

    /**
     * Executes the todo command to add a todo task
     * to the list of tasks, and update the storage file.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
     * @return Boolean of whether to exit the application.
     * */
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
