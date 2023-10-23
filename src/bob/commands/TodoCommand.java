package bob.commands;

import bob.tasklist.TaskList;
import bob.todo.Todo;

/**
 * Adds a new todo to task list.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    private final Todo newTodo;

    /**
     * Creates a new TodoCommand to add a new {@code Todo}.
     *
     * @param todo Todo to add
     */
    public TodoCommand(Todo todo) {
        newTodo = todo;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleCreateTodo(newTodo);
    }

}
