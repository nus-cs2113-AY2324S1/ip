package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;

public class MarkCommand extends Command {
    public static final String TYPE = "mark";
    private static final String USAGE = TYPE + " ((index))";

    private int index;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (CommandParser.isNotOneWord(args)) {
            throwArgumentException(ERROR_MSG_INVALID_NUMBER_OF_ARGS);
        }
        // Input index starts with 1, logical index starts with 0.
        try {
            index = Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throwArgumentException(ERROR_MSG_INDEX_NOT_INTEGER);
        }
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
    public void run(Nuke nuke) throws InvalidCommandArgumentException {
        if(index < 0 || index >= nuke.countTasks()) {
            throwArgumentException(ERROR_MSG_INDEX_INVALID_VALUE);
        }
        nuke.markTask(index);
    }

    private static final String ERROR_MSG_INVALID_NUMBER_OF_ARGS =
            "Command 'mark' should have one argument, index of the task.";
    private static final String ERROR_MSG_INDEX_NOT_INTEGER =
            "Command 'mark' should have a number for index of the task.";
    private static final String ERROR_MSG_INDEX_INVALID_VALUE =
            "The value of index is invalid. Please check the number of tasks.";
}
