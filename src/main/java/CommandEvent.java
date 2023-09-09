public class CommandEvent extends Command {
    public String name;
    public String from;
    public String to;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgumentException(ERROR_MSG_NO_ARGS);
        } else if (Parser.isNotContainingExactOneLabel(args, "/from")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_INVALID_NUMBER_OF_FROM_TO);
        } else if (Parser.isNotContainingExactOneLabel(args, "/to")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_INVALID_NUMBER_OF_FROM_TO);
        } else if (Parser.matches(args, "/from(.*)")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_NAME_EMPTY);
        } else if (Parser.matches(args, "(.+)\\s/from\\s/to(.*)")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_FROM_EMPTY);
        } else if (Parser.matches(args, "(.+)\\s/from\\s(.+)\\s/to")) {
            throw new InvalidCommandArgumentException(ERROR_MSG_TO_EMPTY);
        }

        String[] parsedArgs = Parser.parseArguments(args, "(.+)\\s/from\\s(.+)\\s/to\\s(.+)");
        name = parsedArgs[0];
        from = parsedArgs[1];
        to = parsedArgs[2];
    }

    @Override
    protected String getUsage() {
        return "event ((name)) /from ((start)) /to ((end))";
    }

    @Override
    public void run() {
        Nuke.addEvent(name, from, to);
    }

    private static final String ERROR_MSG_NO_ARGS =
            "Command 'event' should have arguments.";
    private static final String ERROR_MSG_INVALID_NUMBER_OF_FROM_TO =
            "Command 'event' should have one '/from' label and one '/to' label for the period of event.";
    private static final String ERROR_MSG_NAME_EMPTY =
            "Command 'event' should have a string for name of the task.";
    private static final String ERROR_MSG_FROM_EMPTY =
            "Command 'event' should have a string, after '/from' label, for start period of the task.";
    private static final String ERROR_MSG_TO_EMPTY =
            "Command 'event' should have a string, after '/to' label, for end period of the task.";
}
