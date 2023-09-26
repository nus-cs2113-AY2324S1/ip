package rene.command;

import rene.storage.Storage;
import rene.task.Task;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandEvent extends Command{
    private String userInput;
    public CommandEvent(String userInput){
        super(CommandType.EVENT);
        this.userInput = userInput;
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.EVENT, true);
        dataStorage.updateData(tasks);
    }
}
