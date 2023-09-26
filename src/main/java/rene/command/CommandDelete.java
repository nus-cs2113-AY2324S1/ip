package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandDelete extends Command {
    private int taskIndex;
    public CommandDelete(int taskIndex){
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        String taskName = tasks.viewTaskByIndex(taskIndex);
        if(!taskName.equals("Task Not Found")) {
            tasks.deleteTaskByIndex(taskIndex);
            dataStorage.updateData(tasks);
        }
    }
}
