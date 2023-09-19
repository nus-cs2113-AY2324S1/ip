package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;

public class ByeCommand extends Command {

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (!args.isEmpty()) {
            throwArgumentException(ERROR_MSG_TOO_MANY_ARGS);
        }
    }

    @Override
    protected String getUsage() {
        return "bye";
    }

    @Override
    public void run(Nuke nuke) {
        nuke.quit();
    }

    private static final String ERROR_MSG_TOO_MANY_ARGS =
            "Command 'bye' should have no arguments.";
}
