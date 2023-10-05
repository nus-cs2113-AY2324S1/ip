package luke.actions;

import luke.files.Storage;
import luke.tasks.*;
import luke.user.LukeTimeError;
import luke.user.Ui;

public abstract class Command {
    protected ActionType theAction;
    protected String parameters; //no. or description
    private boolean isExit;

    public Command(ActionType theAction, String parameters) {
        this.theAction = theAction;
        this.parameters = parameters;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //ui has String echo, storage has ArrayList<Task> tasks, tasks has ArrayList<Task> mainTaskList;

        //do nothing? should not be executed
    }

    public boolean isExit() {
        return isExit;
    }
}
