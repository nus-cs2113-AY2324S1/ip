package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;
import nuke.Parser;

public class CommandDeadline extends Command {
    public String name;
    public String by;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgumentException(ERROR_MSG_NO_ARGS);
        } else if (Parser.isNotContainingExactOneLabel(args, "/by")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_INVALID_NUMBER_OF_BY);
        } else if (Parser.matches(args, "/by(.*)")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_NAME_EMPTY);
        } else if (Parser.matches(args, "(.+)\\s/by")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_BY_EMPTY);
        }

        String[] parsedArgs = Parser.parseArguments(args, "(.+)\\s/by\\s(.+)");
        checkForbiddenCharacters(parsedArgs);
        name = parsedArgs[0];
        by = parsedArgs[1];
    }

    @Override
    protected String getUsage() {
        return "deadline ((name)) /by ((deadline))";
    }

    @Override
    public void run() {
        Nuke.addDeadline(name, by);
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
