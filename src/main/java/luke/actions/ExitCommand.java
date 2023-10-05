package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

public class ExitCommand extends Command {
    public ExitCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IllegalArgumentException{
        storage.store(tasks);
        setIsExit(true);
        ui.closeUi();
    }
}
