package mudmud.command;

import mudmud.parser.Parser;
import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

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
        Parser parser = new Parser();

        try {
            int selectedIndex = parser.parseIndex(index);
            tasks.setMarkAsDone(selectedIndex);
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
