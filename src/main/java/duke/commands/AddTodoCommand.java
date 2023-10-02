package duke.commands;

import duke.exception.DukeException;
import duke.tasks.Tasklist;
import duke.tasks.Todo;

import duke.ui.Ui;
import static duke.ui.MessageConstants.MESSAGE_ADD;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs a new AddTodoCommand object with the given description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand by adding a new todo task to the Tasklist.
     *
     * @param tasks The Tasklist object to modify.
     * @throws DukeException If there is an error adding the todo task.
     */
    @Override
    public void execute(Tasklist tasks) throws DukeException {
        tasks.add(new Todo(description));
        Ui.showMessage(MESSAGE_ADD + tasks.get(tasks.size() - 1).toString());
    }
    
}