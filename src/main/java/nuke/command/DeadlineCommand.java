package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;

public class DeadlineCommand extends Command {
    public static final String TYPE = "deadline";
    private static final String BY_LABEL = "/by";
    private static final String USAGE = TYPE + " ((name)) " + BY_LABEL + " ((deadline))";
    private static final String REGEX_NAME_EMPTY = BY_LABEL + "(.*)";
    private static final String REGEX_BY_EMPTY = "(.+)\\s" + BY_LABEL;
    private static final String REGEX_PARSE = "(.+)\\s" + BY_LABEL + "\\s(.+)";

    private String name;
    private String by;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throwArgumentException(ERROR_MSG_NO_ARGS);
        } else if (CommandParser.isNotContainingExactOneLabel(args, BY_LABEL)) {
            throwArgumentException(ERROR_MSG_INVALID_NUMBER_OF_BY);
        } else if (CommandParser.matches(args, REGEX_NAME_EMPTY)) {
            throwArgumentException(ERROR_MSG_NAME_EMPTY);
        } else if (CommandParser.matches(args, REGEX_BY_EMPTY)) {
            throwArgumentException(ERROR_MSG_BY_EMPTY);
        }

        String[] parsedArgs = CommandParser.parseArguments(args, REGEX_PARSE);
        checkForbiddenCharacters(parsedArgs);
        name = parsedArgs[0];
        by = parsedArgs[1];
    }

    @Override
    protected String getType() {
        return TYPE;
    }

    @Override
    protected String getUsage() {
        return USAGE;
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
