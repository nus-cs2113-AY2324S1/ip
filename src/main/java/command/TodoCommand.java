package command;

import task.TaskList;
import java.util.Scanner;
import exception.FrankException;
import task.Todo;
import utility.Ui;

public class TodoCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws FrankException {
        String description = ui.getTodo();
        tasks.addTask(new Todo(description));
    }
}
