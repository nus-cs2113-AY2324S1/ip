package commands;

import exceptions.UserInputValidation;
import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Task;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;

/**
 * Marks a task as not done from the task list in the Zran application.
 * Extends the abstract Commands class.
 */
public class UnmarkTaskCommand extends Commands {
    /**
     * Constructs an UnmarkTaskCommand instance with the given input.
     *
     * @param input User's input into the application.
     */
    public UnmarkTaskCommand(String input) {
        super(input);
    }

    /**
     * Executes the unmark task command by calling the 'unmarkTask' function.
     *
     * @param tasks   The task list of class 'TaskList' where the task is to be added.
     * @param ui      The ui component of class 'UI' for displaying messages.
     * @param storage The storage component of class 'Storage' for saving task data. (Not used in this function)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        unmarkTask(tasks, ui);
    }

    /**
     * Marks a task from the task list as not done based on the provided user input.
     * This function validates user inputs, ensuring they are in the correct format.
     *
     * @param tasks The task list of class 'TaskList' where the task is to be added.
     * @param ui    The ui component of class 'UI' for displaying messages.
     */
    private void unmarkTask(TaskList tasks, Ui ui) {
        int taskIndex;
        try {
            taskIndex = UserInputValidation.validateTaskIndex(input, tasks, Commands.UNMARK_TASK_COMMAND);
            Task task = tasks.listItems.get(taskIndex);
            task.setAsNotDone();
            Ui.echo(task, task.isDone);
        } catch (ZranExceptions e) {
            ui.showError(e.getMessage());
        }
    }

}
