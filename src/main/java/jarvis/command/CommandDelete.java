package jarvis.command;
import jarvis.exception.JarvisException;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Command delete a task
 */
public class CommandDelete extends Command {
    private final int taskIndex;
    /**
     * Constructor to create a new CommandDelete object.
     *
     * @param taskIndex The index of the task in the task list that is to be deleted.
     */
    public CommandDelete(int taskIndex){
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }
    /**
     * Executes the command to delete a task from the task list.
     * This method will delete a task from the task list and update the storage file accordingly.
     *
     * @param ui          The Ui object, responsible for user interactions.
     * @param dataStorage The Storage object, responsible for updating task data in the file.
     * @param tasks       The TaskList object, which manages the list of tasks.
     * @throws JarvisException if an invalid task index is provided or other exceptions while deleting a task.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) throws JarvisException {
        tasks.deleteTask(taskIndex);
        dataStorage.updateFile(tasks);
    }
}
