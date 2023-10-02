package dawson.command;

import dawson.ui.Messages;

public class ExitCommand extends Command {

    @Override
    public CommandResult execute() {
        String msg = Messages.MESSAGE_BYE;
        return new CommandResult(msg);
    }
    
}
