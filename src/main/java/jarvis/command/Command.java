package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

public class Command {
    public enum CommandType {TODO, DEADLINE, EVENT, DELETE, LIST, MARK, UNMARK, EXIT};
    private final CommandType commandType;
    public Command(CommandType commandType){
        this.commandType = commandType;
    }

    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){}

    public boolean toExit(){
        return commandType == CommandType.EXIT;
    }
}
