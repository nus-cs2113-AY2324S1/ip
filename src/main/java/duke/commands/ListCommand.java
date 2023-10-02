package duke.commands;

import duke.tasks.Tasklist;

import duke.ui.Ui;

public class ListCommand extends Command {
    
    @Override
    public void execute(Tasklist tasks) {
        Ui.showMessage(tasks.tasksToString());
    }
    
}
