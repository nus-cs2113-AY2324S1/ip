package commands;

import exceptions.UserInputValidation;
import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Task;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;


public class DeleteTaskCommand extends Commands {
    public DeleteTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        deleteTask(tasks, ui);
    }

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
