package rene.command;

import rene.storage.Storage;
import rene.tasklist.TaskList;
import rene.ui.Ui;

public class Command {
    public enum CommandType {TODO, DEADLINE, EVENT, DELETE, LIST, MARK, UNMARK, FIND, EXIT};
    private CommandType commandType;
    public Command(){}
    public Command(CommandType commandType){
        this.commandType = commandType;
    }

    public void executeCommand(Ui ui, Storage dataStorage, TaskList tasks){}
    public boolean toExit(){
        return commandType == CommandType.EXIT;
    }
}
