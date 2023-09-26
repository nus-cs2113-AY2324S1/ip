package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandUnmark extends Command {
    private int taskIndex;
    public CommandUnmark(int taskIndex){
        super(CommandType.UNMARK);
        this.taskIndex = taskIndex;
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        String taskName = tasks.viewTaskByIndex(taskIndex);
        if(!taskName.equals("Task Not Found")) {
            tasks.markTaskAsNotDone(taskIndex);
            dataStorage.updateData(tasks);
        }
    }
}
