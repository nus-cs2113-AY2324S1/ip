package duke.command;

import duke.exception.DukeTaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

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
