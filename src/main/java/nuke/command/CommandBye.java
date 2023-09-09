package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;

public class CommandBye extends Command {

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgumentException(ERROR_MSG_TOO_MANY_ARGS);
        }
    }

    @Override
    protected String getUsage() {
        return "bye";
    }

    @Override
    public void run() {
        Nuke.quit();
    }

    private static final String ERROR_MSG_TOO_MANY_ARGS =
            "Command 'bye' should have no arguments.";
}
