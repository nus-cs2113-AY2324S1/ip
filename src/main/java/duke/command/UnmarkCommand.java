package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

import java.io.IOException;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {

    private String index;

    /**
     * Creates an unmark command.
     *
     * @param index The selected index.
     */
    public UnmarkCommand(String index) {
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
            tasks.setUnmarkAsDone(index);
            storage.updateTaskDatabase(selectedIndex, false);
            ui.printModifiedTask(tasks.getTask(selectedIndex), false);
        } catch (IOException exception) {
            ui.handleIOException(exception);
        } catch (IndexOutOfBoundsException exception) {
            ui.handleIndexOutOfBoundsException(tasks.getTasksCount());
        } catch (NumberFormatException exception) {
            ui.handleNumberFormatException(index);
        }
    }
}
