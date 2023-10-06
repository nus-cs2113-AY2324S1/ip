package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

import static luke.actions.ActionType.MARK;
import static luke.actions.ActionType.UNMARK;

/**
 * The MarkCommand Class represents a command for marking or unmarking tasks as done in the Luke application.
 * It extends the Command class and includes specific behavior for marking and unmarking tasks.
 */
public class MarkCommand extends Command {
    private boolean isDone;


    /**
     * Constructs a MarkCommand with the specified action type and parameters.
     *
     * @param theAction   The action type (MARK or UNMARK).
     * @param parameters  The parameters provided with the command.
     */
    public MarkCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);

        if (theAction == MARK) {
            isDone = true;
        }
        if (theAction == UNMARK) {
            isDone = false;
        }
    }

    public void printIntegerGuide(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\tNo tasks in task list. Please add a task before using " + theAction + ".");
        } else {
            System.out.println("\tPlease input an integer from 1 to " + tasks.size() + ".");
        }
    }

    /**
     * Executes the MarkCommand to mark or unmark a task as done in the task list.
     *
     * @param tasks    The task list where the task should be marked or unmarked.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving task changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(parameters) - 1;
            tasks.get(taskNumber).setDone(isDone);
            System.out.println(tasks.get(taskNumber));
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Your argument for " + theAction + " exceeds your task list.");
        } catch (NumberFormatException e) {
            System.out.println("\t☹ OOPS!!! Your argument for " + theAction + " need to be an integer.");
        }
        printIntegerGuide(tasks);
    }
}
