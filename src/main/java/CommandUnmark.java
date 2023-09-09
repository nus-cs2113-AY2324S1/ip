public class CommandUnmark extends Command {
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
        return "unmark ((index))";
    }

    @Override
    public void run() {
        Nuke.unmarkTask(index);
    }

    private static final String ERROR_MSG_INVALID_NUMBER_OF_ARGS =
            "Command 'unmark' should have one argument, index of the task.";
    private static final String ERROR_MSG_INDEX_NOT_INTEGER =
            "Command 'unmark' should have a number for index of the task.";
    private static final String ERROR_MSG_INDEX_INVALID_VALUE =
            "The value of index is invalid. Please check the number of tasks.";
}
