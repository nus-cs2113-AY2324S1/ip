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
 * Deletes a task from the task list in the Zran application.
 * Extends the abstract Commands class.
 */
public class DeleteTaskCommand extends Commands {
    /**
     * Constructs a DeleteTaskCommand instance with the given input.
     *
     * @param input User's input into the application.
     */
    public DeleteTaskCommand(String input) {
        super(input);
    }

    /**
     * Executes the delete task command by removing a task from the task list.
     *
     * @param tasks   The task list of class 'TaskList' where the task is to be deleted.
     * @param ui      The ui component of class 'UI' for displaying messages.
     * @param storage The storage component of class 'Storage' for saving task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        deleteTask(tasks, ui);
    }

    /**
     * Removes a task from the task list based on the provided user input.
     * This function validates user inputs, ensuring they are in the correct format.
     *
     * @param tasks The task list of class 'TaskList' where the task is to be deleted.
     * @param ui    The ui component of class 'UI' for displaying messages.
     */
    private void deleteTask(TaskList tasks, Ui ui) {
        try {
            int deleteIndex = UserInputValidation.validateTaskIndex(input, tasks, Commands.DELETE_TASK_COMMAND);
            Task deletedTask = tasks.listItems.remove(deleteIndex);
            Ui.echo(tasks.listItems, deletedTask, input);
        } catch (ZranExceptions e) {
            ui.showError(e.getMessage());
        }
    }

}
