package commands;

import taskmanagement.Task;
import taskmanagement.Storage;
import taskmanagement.TaskList;
import userinputs.Ui;

import java.util.ArrayList;

public class ListTaskCommand extends Commands {
    public ListTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.echo(tasks.listItems);
    }

}
