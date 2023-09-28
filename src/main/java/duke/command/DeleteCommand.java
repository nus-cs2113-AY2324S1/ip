package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

import java.io.IOException;

public class DeleteCommand extends Command {

    private String index;

    public DeleteCommand(String index) {
        super(false);
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList tasks, TextUi ui, Storage storage) {
        int selectedIndex;

        try {
            selectedIndex = tasks.deleteTask(index);
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
