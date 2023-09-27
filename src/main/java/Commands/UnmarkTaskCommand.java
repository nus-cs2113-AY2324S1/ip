package Commands;

import exceptions.ZranErrorMessages;
import exceptions.ZranExceptions;
import taskmanagement.Task;
import taskmanagement.TaskList;
import taskmanagement.Storage;
import userinputs.Ui;

import java.util.ArrayList;

public class UnmarkTaskCommand extends Commands {
    static String outputFormat = "    ____________________________________________________________\n" +
            "    %s\n    ____________________________________________________________ ";
    public UnmarkTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Task task;
        try {
            String eventIndex = input.substring(Ui.UNMARK_TASK_COMMAND.length()).trim();
            if (eventIndex.isEmpty()) {
                throw new ZranExceptions(ZranErrorMessages.EMPTY_TASK_INDEX.message);
            }
            try {
                int taskIndex = Integer.parseInt(eventIndex) - 1;
                if (taskIndex < 0 || taskIndex >= tasks.size()) {
                    throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
                }
                task = tasks.get(taskIndex);
                task.setAsNotDone();
                TaskList.echo(task, task.isDone);
            } catch (NumberFormatException e) {
                throw new ZranExceptions(ZranErrorMessages.INVALID_TASK_INDEX.message);
            }
        } catch (ZranExceptions e) {
            ui.showError(e.getMessage());
        }
    }
}
