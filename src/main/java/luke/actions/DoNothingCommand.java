package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;


public class DoNothingCommand extends Command {
    public DoNothingCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}

