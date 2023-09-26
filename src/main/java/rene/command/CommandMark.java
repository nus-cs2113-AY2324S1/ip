package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandMark extends Command {
    private int taskIndex;
    public CommandMark(int taskIndex){
        super(CommandType.MARK);
        this.taskIndex = taskIndex;
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        String taskName = tasks.viewTaskByIndex(taskIndex);
        if(!taskName.equals("Task Not Found")) {
            tasks.markTaskAsDone(taskIndex, true);
            dataStorage.updateData(tasks);
        }
    }
}
