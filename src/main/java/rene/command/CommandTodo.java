package rene.command;

import rene.storage.Storage;
import rene.task.Task;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class CommandTodo extends Command {

    private String userInput;
    public CommandTodo(String userInput){
        super(CommandType.TODO);
        this.userInput = userInput;
    }
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.TODO, true);
        dataStorage.updateData(tasks);
    }

}
