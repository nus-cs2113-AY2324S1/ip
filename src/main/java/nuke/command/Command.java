package nuke.command;

import nuke.Nuke;
import nuke.command.exception.InvalidCommandArgumentException;

import java.util.Arrays;

/**
 * Represents command which can be input by users.
 */
public abstract class Command {

    /**
     * Parses arguments and then applies the result to set the values.
     * @param args command argument
     * @throws InvalidCommandArgumentException if parsing argument fails
     */
    public abstract void applyArguments(String args) throws InvalidCommandArgumentException;

    /**
     * Returns the type of the command.
     *
     * @return the type of the command.
     */
    protected abstract String getType();

    /**
     * Returns the usage of the command.
     *
     * @return the usage of the command.
     */
    protected abstract String getUsage();

    /**
     * Runs the command.
     *
     * @param nuke the Nuke which is running this command.
     * @throws InvalidCommandArgumentException if running fails due to one or more arguments
     */
    public abstract void run(Nuke nuke) throws InvalidCommandArgumentException;

    /**
     * Checks if one of forbidden characters exists,
     * and throws exception if it does.
     * @param parsedArgs array of parsed arguments
     * @throws InvalidCommandArgumentException if one of forbidden characters exists in the arguments
     */
    protected void checkForbiddenCharacters(String[] parsedArgs)
            throws InvalidCommandArgumentException {
        if (Arrays.stream(parsedArgs).anyMatch(s -> s.contains(FORBIDDEN_CHARACTERS))) {
            String errorMessage = String.format(ERROR_MSG_FORBIDDEN_CHARACTERS, getType());
            throwArgumentException(errorMessage);
        }
    }

    /**
     * Throws an argument exception.
     * @param reason reason of the exception
     * @throws InvalidCommandArgumentException with a reason and the usage of the command
     */
    protected void throwArgumentException(String reason)
            throws InvalidCommandArgumentException {
        throw new InvalidCommandArgumentException(reason, "Usage: " + getUsage());
    }

    private static final String FORBIDDEN_CHARACTERS = "/";
    private static final String ERROR_MSG_FORBIDDEN_CHARACTERS =
            "Command '%s' should not contain '" + FORBIDDEN_CHARACTERS + "' in arguments.";
}
