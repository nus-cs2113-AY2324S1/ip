package commands;

import taskmanagement.Task;
import taskmanagement.Storage;
import taskmanagement.TaskList;
import userinputs.Ui;

import java.util.ArrayList;


public class HelpTaskCommand extends Commands {
    public HelpTaskCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showHelp();
    }

}
