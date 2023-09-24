package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class InvalidCommand extends Command {

    public final String errorFeedback;

    public InvalidCommand(String errorFeedback) {
        this.errorFeedback = errorFeedback;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        ui.displayError(this.errorFeedback);
        return false;
    }
}
