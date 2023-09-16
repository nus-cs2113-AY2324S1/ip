package nuke.command;

import nuke.Nuke;
import nuke.command.exception.InvalidCommandArgumentException;

public class CommandDelete extends Command {
    private int index;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (CommandParser.isNotOneWord(args)) {
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
        return "delete ((index))";
    }

    @Override
    public void run() {
        Nuke.deleteTask(index);
    }

    private static final String ERROR_MSG_INVALID_NUMBER_OF_ARGS =
            "Command 'delete' should have one argument, index of the task.";
    private static final String ERROR_MSG_INDEX_NOT_INTEGER =
            "Command 'delete' should have a number for index of the task.";
    private static final String ERROR_MSG_INDEX_INVALID_VALUE =
            "The value of index is invalid. Please check the number of tasks.";
}
