package jarvis.command;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
/**
 * Command to add a Event task
 */
public class CommandEvent extends Command {
    private final String userInput;
    /**
     * Constructor to create a new CommandEvent object.
     *
     * @param userInput String input provided by the user which contains details about the task.
     */
    public CommandEvent(String userInput){
        super(CommandType.EVENT);
        this.userInput = userInput;
    }
    /**
     * Override the executeCommand method from the Command class to execute the addition of an Event task.
     *
     * @param ui         The Ui object to interact with the user.
     * @param dataStorage The Storage object to update the task data in the data file.
     * @param tasks       The TaskList object that holds and manages the tasks.
     */
    @Override
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){
        tasks.addToTaskList(userInput, Task.TaskType.EVENT, true);
        dataStorage.updateFile(tasks);
    }
}
