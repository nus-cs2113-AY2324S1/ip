package command;

import task.Deadline;
import task.TaskList;
import exception.FrankException;
import utility.Ui;

public class DeadlineCommand extends Command {
    /**
     * Executes creation and adding of a Deadline
     * @param tasks TaskList of current tasks
     * @param ui Current User Interface
     * @throws FrankException Unique Exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException {
        String[] descDueDate = ui.getDeadline();
        tasks.addTask(new Deadline(descDueDate[0], descDueDate[1]));
    }
}
