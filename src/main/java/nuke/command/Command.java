package nuke.command;

import nuke.Nuke;
import nuke.command.exception.InvalidCommandArgumentException;

import java.util.Arrays;

public abstract class Command {

    public abstract void applyArguments(String args) throws InvalidCommandArgumentException;

    abstract String getUsage();

    public abstract void run(Nuke nuke) throws InvalidCommandArgumentException;

    protected void checkForbiddenCharacters(String[] parsedArgs)
            throws InvalidCommandArgumentException {
        if (Arrays.stream(parsedArgs).anyMatch(s -> s.contains(FORBIDDEN_CHARACTERS))) {
            String errorMessage = String.format(ERROR_MSG_FORBIDDEN_CHARACTERS, getType());
            throwArgumentException(errorMessage);
        }
    }

    protected String getType() {
        return getUsage().split(" ")[0];
    }

    protected void throwArgumentException(String reason)
            throws InvalidCommandArgumentException {
        throw new InvalidCommandArgumentException(reason, "Usage: " + getUsage());
    }

    private static final String FORBIDDEN_CHARACTERS = "/";
    private static final String ERROR_MSG_FORBIDDEN_CHARACTERS =
            "Command '%s' should not contain '" + FORBIDDEN_CHARACTERS + "' in arguments.";
}
