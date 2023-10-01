package command;

import exception.FrankException;
import task.TaskList;
import utility.Ui;

import java.io.IOException;
import java.util.Scanner;
import static utility.Constants.SOLIDLINE;

/**
 * Clears everything from the task list
 * This will also clear storage as well as it saves immediately after
 */
public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException, IOException {
        boolean isConfirmed = ui.confirmExecute();
        if(isConfirmed) {
            tasks.clearTasks();
        }
    }

}