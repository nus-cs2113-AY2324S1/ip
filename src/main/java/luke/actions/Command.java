package luke.actions;

import luke.files.Storage;
import luke.tasks.*;
import luke.user.Ui;

/**
 * The Command class represents a generic command in the LukeTime application.
 * It serves as the base class for specific command types, such as AddCommand, ListCommand, etc.
 */
public abstract class Command {

    /**
     * The type of action associated with the command.
     */
    protected ActionType theAction;

    /**
     * Additional parameters provided with the command (e.g., task description or task number).
     */
    protected String parameters;

    /**
     * Flag indicating whether the command triggers an exit from the application.
     */
    private boolean isExit;

    /**
     * Constructs a Command with the specified action type and parameters.
     *
     * @param theAction   The type of action associated with the command.
     * @param parameters  Additional parameters provided with the command.
     */
    public Command(ActionType theAction, String parameters) {
        this.theAction = theAction;
        this.parameters = parameters;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks    The task list used for command execution.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving task changes (not used in all commands).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //do nothing? should not be executed
    }

    /**
     * Sets the exit flag to indicate whether the command triggers an exit from the application.
     *
     * @param isExit True if the command triggers an exit, false otherwise.
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Checks if the command triggers an exit from the application.
     *
     * @return True if the command triggers an exit, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
