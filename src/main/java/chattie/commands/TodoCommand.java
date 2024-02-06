package chattie.commands;

import chattie.TaskList;
import chattie.Ui;
import chattie.error.ChattieException;
import chattie.error.ErrorType;
import chattie.Storage;
import chattie.tasks.Deadline;
import chattie.tasks.Todo;

/**
 * Deals with todo related commands
 */
public class TodoCommand extends Command {

    private static final int TODO_LENGTH = 5;
    private static String command;

    public TodoCommand(String command) {
        this.command = command;
    }

    /**
     * Adds a new todo to list of tasks
     *
     * @param tasks List of tasks
     * @param ui User interface
     * @throws ChattieException If command is empty or not formatted properly
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws ChattieException {
        if (command.trim().length() < TODO_LENGTH) {
            throw new ChattieException(ErrorType.TODO);
        }
        String task = command.substring(TODO_LENGTH);

        Todo todo = new Todo(task);
        tasks.add(todo);
        ui.printAddMessage(tasks, todo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
