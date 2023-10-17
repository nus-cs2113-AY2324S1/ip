package duke;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

