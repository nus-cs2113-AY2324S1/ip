package duke;

import java.util.ArrayList;

public class DeleteCommand implements Command {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        TaskList.deleteTask(input, tasks); // Call the modified deleteTask method with the task index
    }

    @Override
    public boolean isExit() {
        return false; // This command does not indicate program exit
    }

}


