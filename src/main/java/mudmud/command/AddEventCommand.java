package mudmud.command;

import mudmud.exception.DukeTaskException;
import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * Represents an add event command.
 */
public class AddEventCommand extends AddCommand {

    /**
     * Creates an add event command.
     *
     * @param data The inputted data.
     */
    public AddEventCommand(String data) {
        super(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        try {
            String dataString = tasks.addEvent(dataToAdd);
            storage.addNewData(dataString, tasks.getTasksCount());
            ui.printRecentTask(tasks);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        } catch (DukeTaskException exception) {
            exception.handleDukeTaskException("event", dataToAdd);
        } catch (DateTimeException exception) {
            ui.handleDateTimeException();
        }
    }
}
