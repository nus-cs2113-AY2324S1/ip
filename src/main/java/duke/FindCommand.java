package duke;

import java.util.ArrayList;

public class FindCommand implements Command {
    private final String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        TaskList.findTask(input, tasks);
    }

    @Override
    public boolean isExit() {
        return false; // This command does not indicate program exit
    }

}
