package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class CommandToDo extends Command {
    private final String userInput;
    public CommandToDo(String userInput){
        super(CommandType.TODO);
        this.userInput = userInput;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.TODO, true);
        //TODO: add to storage
    }

}
