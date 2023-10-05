package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(parameters) - 1;
            System.out.println("\tNoted. I've removed this task:\n" + tasks.get(taskNumber));
            tasks.removeTask(taskNumber);
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! Your arguments for " + theAction + " exceeds your task list.");
        }
    }
}
