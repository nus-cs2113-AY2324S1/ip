package dawson.command;

import dawson.task.TaskList;
import dawson.ui.Messages;

/**
 * Represents an invalid command. Upon execution, return feedback to the user.
 */
public class InvalidCommand extends Command {

    @Override
    public CommandResult execute(TaskList list) {
        String errorMessage = Messages.MESSAGE_INVALID_COMMAND;
        return new CommandResult(errorMessage);
    }
    
}
