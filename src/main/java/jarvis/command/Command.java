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
    public enum CommandType {TODO, DEADLINE, EVENT, DELETE, LIST, MARK, UNMARK, EXIT};
    private CommandType commandType = null;
    public Command(CommandType commandType){
        this.commandType = commandType;
    }

    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks) throws JarvisException {}

    public boolean toExit(){
        return commandType == CommandType.EXIT;
    }
}
