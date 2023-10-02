package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

import java.io.IOException;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {

    private String index;

    /**
     * Creates a mark command.
     *
     * @param index The selected index.
     */
    public MarkCommand(String index) {
        super(false);
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        try {
            int selectedIndex = Integer.parseInt(index);
            tasks.setMarkAsDone(index);
            storage.updateTaskDatabase(selectedIndex, true);
            ui.printModifiedTask(tasks.getTask(selectedIndex), true);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        } catch (IndexOutOfBoundsException exception) {
            ui.handleIndexOutOfBoundsException(tasks.getTasksCount());
        } catch (NumberFormatException exception) {
            ui.handleNumberFormatException(index);
        }
    }
}
