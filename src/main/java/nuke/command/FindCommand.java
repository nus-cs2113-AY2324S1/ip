package nuke.command;

import nuke.Nuke;
import nuke.command.exception.InvalidCommandArgumentException;

public class FindCommand extends Command {
    public static final String TYPE = "find";
    private static final String USAGE = TYPE + " ((keyword))";

    private String keyword;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throwArgumentException(ERROR_MSG_NO_ARGS);
        }
        checkForbiddenCharacters(new String[]{args});
        keyword = args;
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
        nuke.findTasks(keyword);
    }

    private static final String ERROR_MSG_NO_ARGS =
            "Command 'find' should have one argument, keyword for matching.";
}
