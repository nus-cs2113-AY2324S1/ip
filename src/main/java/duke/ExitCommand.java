package duke;

// ExitCommand class for handling the "bye" command
public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }

    @Override
    public boolean isExit() {
        return true; // This command indicates program exit
    }
}

