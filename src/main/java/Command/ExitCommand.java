package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "bye";
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayGoodbye();
        return true;
    }
}
