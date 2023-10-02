package dawson.command;

import dawson.ui.Messages;

public class InvalidCommand extends Command {

    @Override
    public CommandResult execute() {
        String errorMessage = Messages.MESSAGE_INVALID_COMMAND;
        return new CommandResult(errorMessage);
    }
    
}
