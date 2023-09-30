package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

import java.io.IOException;

public class UnmarkCommand extends Command {

    private String index;

    public UnmarkCommand(String index) {
        super(false);
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        try {
            int selectedIndex = tasks.setUnmarkAsDone(index);
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
