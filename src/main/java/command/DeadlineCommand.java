package command;

import task.Deadline;
import task.TaskList;
import exception.FrankException;
import utility.Ui;

public class DeadlineCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException {
        String[] descDueDate = ui.getDeadline();
        tasks.addTask(new Deadline(descDueDate[0], descDueDate[1]));
    }
}
