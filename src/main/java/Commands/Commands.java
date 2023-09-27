package Commands;


import taskmanagement.Task;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;

public abstract class Commands {
    protected String input;
    public Commands(String input) {
        this.input = input;
    }

    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage);

    public boolean isExitCommand(String input) {
        return input.equalsIgnoreCase(Ui.EXIT_BOT_COMMAND);
    }
}
