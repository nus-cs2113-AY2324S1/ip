package duke;

import java.util.ArrayList;

// ExitCommand class for handling the "bye" command
public class ExitCommand implements Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }

    @Override
    public boolean isExit() {
        return true; // This command indicates program exit
    }
}

