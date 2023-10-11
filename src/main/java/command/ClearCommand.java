package command;

import exception.FrankException;
import task.TaskList;
import utility.Ui;
import java.io.IOException;

public class ClearCommand extends Command {
    /**
     * Clears everything from the task list
     * This will also clear storage as well as it saves immediately after
     *
     * @param tasks TaskList of current tasks
     * @param ui Current user interface
     * @throws FrankException Unique Exception
     * @throws IOException Input Exception
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException, IOException {
        boolean isConfirmed = ui.confirmExecute();
        if(isConfirmed) {
            tasks.clearTasks();
        }
    }

}