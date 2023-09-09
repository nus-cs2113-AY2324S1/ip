package nuke.command;

import nuke.command.exception.InvalidCommandArgumentException;
import nuke.Nuke;
import nuke.Parser;

public class CommandMark extends Command {
    private int index;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (Parser.isNotOneWord(args)) {
            throw new InvalidCommandArgumentException(ERROR_MSG_INVALID_NUMBER_OF_ARGS);
        }
        // Input index starts with 1, logical index starts with 0.
        try {
            index = Integer.parseInt(args) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentException(ERROR_MSG_INDEX_NOT_INTEGER);
        }
        if(index < 0 || index >= Nuke.getNumberOfTasks()) {
            throw new InvalidCommandArgumentException(ERROR_MSG_INDEX_INVALID_VALUE);
        }
    }

    @Override
    protected String getUsage() {
        return "mark ((index))";
    }

    @Override
    public void run() {
        Nuke.markTask(index);
    }

    private static final String ERROR_MSG_INVALID_NUMBER_OF_ARGS =
            "Command 'mark' should have one argument, index of the task.";
    private static final String ERROR_MSG_INDEX_NOT_INTEGER =
            "Command 'mark' should have a number for index of the task.";
    private static final String ERROR_MSG_INDEX_INVALID_VALUE =
            "The value of index is invalid. Please check the number of tasks.";
}
