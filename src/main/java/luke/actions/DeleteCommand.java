package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

/**
 * The DeleteCommand Class represents a command for deleting a task in the Luke application.
 * It extends the Command class and includes specific behavior for deleting a task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a DeleteCommand with the specified action type and parameters.
     *
     * @param theAction   The action type (DELETE).
     * @param parameters  The parameters provided with the command (task number to delete).
     */
    public DeleteCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    public void printIntegerGuide(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tNo tasks in task list. Please add a task before using " + theAction + ".");
        } else {
            System.out.println("\tPlease input an integer from 1 to " + tasks.size() + ".");
        }
    }

    /**
     * Executes the DeleteCommand to remove a task from the task list.
     *
     * @param tasks    The task list from which the task will be deleted.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving task changes (not used in this case).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(parameters) - 1;
            System.out.println("\tNoted. I've removed this task:\n" + tasks.get(taskNumber));
            tasks.removeTask(taskNumber);
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Your argument for " + theAction + " exceeds your task list.");
        } catch (NumberFormatException e) {
            System.out.println("\t☹ OOPS!!! Your argument for " + theAction + " need to be an integer.");
        }
        printIntegerGuide(tasks);
    }
}
