package commands;

import exceptions.UserInputValidation;
import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Task;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

/**
 * Marks a task as done from the task list in the Zran application.
 * Extends the abstract Commands class.
 */
public class MarkTaskCommand extends Commands{
    /**
     * Constructs a MarkTaskCommand instance with the given input.
     *
     * @param input User's input into the application.
     */
    public MarkTaskCommand(String input){
        super(input);
    }

    /**
     * Executes the mark task command by calling the 'markTask' function.
     *
     * @param tasks   The task list of class 'TaskList' where the task is to be added.
     * @param ui      The ui component of class 'UI' for displaying messages.
     * @param storage The storage component of class 'Storage' for saving task data. (Not used in this function)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        markTask(tasks, ui);
    }

    /**
     * Marks a task from the task list as done based on the provided user input.
     * This function validates user inputs, ensuring they are in the correct format.
     *
     * @param tasks The task list of class 'TaskList' where the task is to be added.
     * @param ui    The ui component of class 'UI' for displaying messages.
     */
    private void markTask(TaskList tasks, Ui ui) {
        try{
            int taskIndex = UserInputValidation.validateTaskIndex(input, tasks, Commands.MARK_TASK_COMMAND);
            Task task = tasks.listItems.get(taskIndex);
            task.setAsDone();
            Ui.echo(task, task.isDone);
        } catch (ZranExceptions e) {
            ui.showError(e.getMessage());
        }
    }

}
