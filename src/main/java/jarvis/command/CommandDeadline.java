package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Command to add a Deadline task
 */
public class CommandDeadline extends Command {
    private final String userInput;
    /**
     * Constructor to create a new CommandDeadline object.
     *
     * @param userInput The raw user input string.
     */
    public CommandDeadline(String userInput){
        super(CommandType.DEADLINE);
        this.userInput = userInput;
    }
    /**
     * Executes the command to add a new deadline task.
     * This method will add a new deadline task to the task list and update the storage.
     *
     * @param ui          The Ui object, responsible for user interactions.
     * @param dataStorage The Storage object, responsible for saving task data to the file.
     * @param tasks       The TaskList object, which manages the list of tasks.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.DEADLINE, true);
        dataStorage.updateFile(tasks);
    }
}
