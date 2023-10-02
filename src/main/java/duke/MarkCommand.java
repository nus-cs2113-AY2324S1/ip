package duke;

public class MarkCommand implements Command {
    private final String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList.markTask(input, tasks);
    }

    @Override
    public boolean isExit() {
        return false; // This command does not indicate program exit
    }

}


