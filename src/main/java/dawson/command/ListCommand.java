package dawson.command;

import dawson.task.TaskList;

public class ListCommand extends Command {
    
    @Override
    public CommandResult execute(TaskList list) {
        String[] listString = list.getTaskStrings();
        return new CommandResult(listString);
    }
    
}
