package mudmud.command;

import mudmud.parser.Parser;
import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

import java.io.IOException;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {

    private String index;

    /**
     * Creates a delete command.
     *
     * @param index The selected task's index.
     */
    public DeleteCommand(String index) {
        super(false);
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        Parser parser = new Parser();

        try {
            int selectedIndex = parser.parseIndex(index);
            tasks.deleteTask(selectedIndex);
            storage.deleteTaskData(selectedIndex);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        } catch (IndexOutOfBoundsException exception) {
            ui.handleIndexOutOfBoundsException(tasks.getTasksCount());
        } catch (NumberFormatException exception) {
            ui.handleNumberFormatException(index);
        }
    }
}
