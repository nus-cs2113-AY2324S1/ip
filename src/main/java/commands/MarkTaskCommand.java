package commands;

import exceptions.UserInputValidation;
import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Task;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

public class MarkTaskCommand extends Commands{
    public MarkTaskCommand(String input){
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        markTask(tasks, ui);
    }

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
