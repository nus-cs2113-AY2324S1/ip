package command;

import task.TaskList;
import java.util.Scanner;
import exception.FrankException;
import task.Todo;
import utility.Ui;

public class TodoCommand extends Command {
    /**
     * Creates and adds a new Todo to the TaskList
     *
     * @param tasks TaskList of current Tasks
     * @param ui Current User Interface
     * @throws FrankException Unique Exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException {
        String description = ui.getTodo();
        tasks.addTask(new Todo(description));
    }
}
