package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * User command to execute an action
 * Will be inherited by the other commands
 */
public class Command {
    public enum CommandType {TODO, DEADLINE, EVENT, DELETE, LIST, MARK, UNMARK, FIND, EXIT};
    private CommandType commandType = null;
    /**
     * Constructs a new 'Command' object.
     *
     * @param commandType The specific type of the command, which is one of the predefined values in the 'CommandType' enum.
     */
    public Command(CommandType commandType){
        this.commandType = commandType;
    }
    /**
     * Executes the command.
     * This method should be overridden by subclasses to provide the specific logic for executing each command type.
     *
     * @param ui          The 'Ui' object to interact with the user interface.
     * @param dataStorage The 'Storage' object to read/write task data from/to storage.
     * @param tasks       The 'TaskList' object to manipulate and hold the task data.
     * @throws JarvisException If any issue arises during the command execution, it throws a custom exception to be handled at a higher level.
     */
    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) throws JarvisException {}
    /**
     * Determines if the command is an exit command.
     *
     * @return A boolean value indicating whether the command type is 'EXIT'.
     */
    public boolean toExit(){
        return commandType == CommandType.EXIT;
    }
}
