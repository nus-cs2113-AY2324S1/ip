package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

public class ExitCommand extends Command {
    //bye input
    public ExitCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IllegalArgumentException{
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;

        storage.store(tasks);
        setIsExit(true);
        ui.closeUi();
    }
}
