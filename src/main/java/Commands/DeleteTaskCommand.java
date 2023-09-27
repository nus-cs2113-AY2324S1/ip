package Commands;

import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Task;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;


public class DeleteTaskCommand extends Commands {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________ ";
    public DeleteTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            String deleteIndex = input.substring(Ui.DELETE_TASK_COMMAND.length()).trim();
            Task deletedTask = deleteTask(tasks, Integer.parseInt(deleteIndex) - 1);
            TaskList.echo(tasks, deletedTask, input);
        } catch (NumberFormatException | NullPointerException | ZranExceptions e) {
            System.out.printf((outputFormat) + "%n", ZranErrorMessages.INVALID_TASK_INDEX_DELETE.message);
        }
    }

    public static Task deleteTask(ArrayList<Task> items, int deleteIndex) throws ZranExceptions{

        Task deletedTask;
        if (deleteIndex<0 || deleteIndex >= items.size()){
            throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX_DELETE.message);
        }
        deletedTask = items.remove(deleteIndex);

        return deletedTask;
    }
}
