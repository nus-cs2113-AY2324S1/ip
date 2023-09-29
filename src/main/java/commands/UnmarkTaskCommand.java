package commands;

import exceptions.UserInputValidation;
import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Task;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;

public class UnmarkTaskCommand extends Commands {

    public UnmarkTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        unmarkTask(tasks, ui);
    }

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
