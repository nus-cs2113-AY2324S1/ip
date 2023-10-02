package duke;

public class UnmarkCommand implements Command {
    private final String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList.unmarkTask(input, tasks);
    }

    @Override
    public boolean isExit() {
        return false; // This command does not indicate program exit
    }

}



