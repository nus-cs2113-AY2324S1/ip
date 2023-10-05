package luke.actions;

import luke.files.Storage;
import luke.tasks.*;
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
        //do nothing? should not be executed
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }
}
