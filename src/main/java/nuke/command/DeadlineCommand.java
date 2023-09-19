package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;

public class DeadlineCommand extends Command {
    public String name;
    public String by;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throwArgumentException(ERROR_MSG_NO_ARGS);
        } else if (CommandParser.isNotContainingExactOneLabel(args, "/by")) {
            throwArgumentException(ERROR_MSG_INVALID_NUMBER_OF_BY);
        } else if (CommandParser.matches(args, "/by(.*)")) {
            throwArgumentException(ERROR_MSG_NAME_EMPTY);
        } else if (CommandParser.matches(args, "(.+)\\s/by")) {
            throwArgumentException(ERROR_MSG_BY_EMPTY);
        }

        String[] parsedArgs = CommandParser.parseArguments(args, "(.+)\\s/by\\s(.+)");
        checkForbiddenCharacters(parsedArgs);
        name = parsedArgs[0];
        by = parsedArgs[1];
    }

    @Override
    protected String getUsage() {
        return "deadline ((name)) /by ((deadline))";
    }

    @Override
    public void run(Nuke nuke) {
        nuke.addDeadline(name, by);
    }

    private static final String ERROR_MSG_NO_ARGS =
            "Command 'deadline' should have two arguments, name and deadline of the task.";
    private static final String ERROR_MSG_INVALID_NUMBER_OF_BY =
            "Command 'deadline' should have one '/by' label for the deadline.";
    private static final String ERROR_MSG_NAME_EMPTY =
            "Command 'deadline' should have a string for name of the task.";
    private static final String ERROR_MSG_BY_EMPTY =
            "Command 'deadline' should have a string for deadline of the task.";
}
