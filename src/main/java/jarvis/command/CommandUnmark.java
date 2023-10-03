package jarvis.command;
import jarvis.exception.JarvisException;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.tasklist.TaskManager;
import jarvis.ui.Ui;

public class CommandUnmark extends Command {
    private final int taskIndex;
    public CommandUnmark(int taskIndex){
        super(CommandType.UNMARK);
        this.taskIndex = taskIndex;
    }

    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) throws JarvisException {
        tasks.markTaskAsUndone(taskIndex);
    }
}
