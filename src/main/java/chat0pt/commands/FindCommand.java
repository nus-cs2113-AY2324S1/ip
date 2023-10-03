package chat0pt.commands;

import chat0pt.storage.Storage;
import chat0pt.tasks.Task;
import chat0pt.tasks.TaskList;
import chat0pt.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String[] commandString;
    public FindCommand(String[] commandString){
        this.commandString = commandString;
    }

    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        ArrayList<Task> foundTasks = tasks.findTasks(commandString);
        if (foundTasks.isEmpty()){
            ui.failedFind();
        } else {
            ui.listHandler(foundTasks);
        }
    }
}
