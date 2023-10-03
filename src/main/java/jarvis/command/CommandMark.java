package jarvis.command;
import jarvis.exception.JarvisException;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
/**
 * Command to Mark a task
 */
public class CommandMark extends Command {
    private final int taskIndex;
    public CommandMark(int taskIndex){
        super(CommandType.MARK);
        this.taskIndex = taskIndex;
    }
    /**
     * Executes the 'mark' command: marks a task as done and updates the storage file accordingly.
     *
     * @param ui          The 'Ui' object to interact with the user interface.
     * @param dataStorage The 'Storage' object to update stored task data.
     * @param tasks       The 'TaskList' object to manipulate and hold the task data.
     * @throws JarvisException If any issue arises during the command execution, it throws a custom exception to be handled at a higher level.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) throws JarvisException {
        tasks.markTaskAsDone(taskIndex, true);
        dataStorage.updateFile(tasks);
    }
}
