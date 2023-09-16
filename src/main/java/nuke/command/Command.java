package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Ui;

import java.util.Arrays;

public abstract class Command {
    private static final String FORBIDDEN_CHARACTERS = "/";
    private static final String ERROR_MSG_FORBIDDEN_CHARACTERS =
            "Command '%s' should not contain '" + FORBIDDEN_CHARACTERS + "' in arguments.";


    public abstract void applyArguments(String args) throws InvalidCommandArgumentException;

    protected void checkForbiddenCharacters(String[] parsedArgs)
            throws InvalidCommandArgumentException {
        if (Arrays.stream(parsedArgs).anyMatch(s -> s.contains(FORBIDDEN_CHARACTERS))) {
            String errorMessage = String.format(ERROR_MSG_FORBIDDEN_CHARACTERS, getType());
            throw new InvalidCommandArgumentException(errorMessage);
        }
    }

    public void handleArgumentError(InvalidCommandArgumentException e) {
        Ui.printCommandError(e.reason, getArgumentErrorDetail());
    }

    private String getArgumentErrorDetail() {
        return "Usage: " + getUsage();
    }

    protected String getType() {
        return getUsage().split(" ")[0];
    }

    protected abstract String getUsage();

    public abstract void run();
}
