package rene.command;

import rene.storage.Storage;
import rene.task.Task;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandDeadline extends Command {
    private String userInput;
    public CommandDeadline(String userInput){
        super(CommandType.DEADLINE);
        this.userInput = userInput;
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.DEADLINE, true);
        dataStorage.updateData(tasks);
    }
}
