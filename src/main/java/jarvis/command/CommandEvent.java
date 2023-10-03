package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class CommandEvent extends Command {
    private final String userInput;

    public CommandEvent(String userInput){
        super(CommandType.EVENT);
        this.userInput = userInput;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.EVENT, true);
        dataStorage.updateFile(tasks);
    }
}
