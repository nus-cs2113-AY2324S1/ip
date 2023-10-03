package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class CommandDeadline extends Command {
    private final String userInput;

    public CommandDeadline(String userInput){
        super(CommandType.DEADLINE);
        this.userInput = userInput;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.DEADLINE, true);
        dataStorage.updateFile(tasks);
    }
}
