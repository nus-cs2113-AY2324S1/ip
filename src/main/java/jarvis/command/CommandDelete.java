package jarvis.command;
import jarvis.exception.JarvisException;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class CommandDelete extends Command {
    private final int taskIndex;
    public CommandDelete(int taskIndex){
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) throws JarvisException {
        tasks.deleteTask(taskIndex);
        dataStorage.updateFile(tasks);
    }
}
