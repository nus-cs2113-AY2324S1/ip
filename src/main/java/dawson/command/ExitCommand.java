package dawson.command;

import dawson.task.TaskList;
import dawson.ui.Messages;

public class ExitCommand extends Command {

    @Override
    public CommandResult execute(TaskList list) {
        String msg = Messages.MESSAGE_BYE;
        return new CommandResult(msg);
    }
    
}
