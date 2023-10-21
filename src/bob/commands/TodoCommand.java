package bob.commands;

import bob.BobException;
import bob.deadline.Deadline;
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

    public TodoCommand(String line) throws BobException {
        if (line.isEmpty()) {
            throw new BobException("The description of a todo cannot be empty");
        }

        if (line.trim().isEmpty()) {
            throw new BobException("The description of a todo cannot be empty");
        }

        newTodo = new Todo(line);
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.handleCreateTodo(newTodo);
    }

}
