package mudmud.command;

import mudmud.parser.Parser;
import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

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
        Parser parser = new Parser();

        try {
            int selectedIndex = parser.parseIndex(index);
            tasks.setUnmarkAsDone(selectedIndex);
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
