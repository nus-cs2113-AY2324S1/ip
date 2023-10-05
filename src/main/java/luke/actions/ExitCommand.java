package luke.actions;

import luke.files.Storage;
import luke.tasks.TaskList;
import luke.user.Ui;

/**
 * The ExitCommand class represents a command for exiting the Luke application.
 * It extends the Command class and includes specific behavior for exiting the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand with the specified action type and parameters.
     *
     * @param theAction   The action type (BYE).
     * @param parameters  The parameters provided with the command (not used in this case).
     */
    public ExitCommand(ActionType theAction, String parameters) {
        super(theAction, parameters);
    }

    /**
     * Executes the ExitCommand to store the task list and close the application.
     *
     * @param tasks    The task list to be stored.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving task changes.
     * @throws IllegalArgumentException If there is an issue with storing the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IllegalArgumentException{
        storage.store(tasks);
        setIsExit(true);
        ui.closeUi();
    }
}
