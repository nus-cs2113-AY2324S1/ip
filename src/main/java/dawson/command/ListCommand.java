package dawson.command;

import dawson.task.TaskList;

public class ListCommand extends Command {

    private TaskList list;

    public ListCommand(TaskList list) {
        this.list = list;
    }

    @Override
    public CommandResult execute() {
        String[] listString = list.getTaskStrings();
        return new CommandResult(listString);
    }
    
}
