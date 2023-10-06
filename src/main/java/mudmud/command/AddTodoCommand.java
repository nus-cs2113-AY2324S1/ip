package mudmud.command;

import mudmud.exception.DukeTaskException;
import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

import java.io.IOException;

/**
 * Represents an add todo command.
 */
public class AddTodoCommand extends AddCommand {

    /**
     * Creates an add todo command.
     *
     * @param data The inputted data.
     */
    public AddTodoCommand(String data) {
        super(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        try {
            String dataString = tasks.addTodo(dataToAdd);
            storage.addNewData(dataString, tasks.getTasksCount());
            ui.printRecentTask(tasks);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        } catch (DukeTaskException exception) {
            exception.handleDukeTaskException("todo", dataToAdd);
        }
    }
}
