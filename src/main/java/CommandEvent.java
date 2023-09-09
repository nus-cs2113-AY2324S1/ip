public class CommandEvent extends Command {
    private static final String NO_ARGUMENTS_ERROR =
            "Command 'event' should have arguments.";
    private static final String FROM_TO_NUMBER_ERROR =
            "Command 'event' should have one '/from' label and one '/to' label for the period of event.";
    private static final String NAME_EMPTY_ERROR =
            "Command 'event' should have a string as a name of the task.";
    private static final String FROM_EMPTY_ERROR =
            "Command 'event' should have a string, after '/from' label, as a start period of the task.";
    private static final String TO_EMPTY_ERROR =
            "Command 'event' should have a string, after '/to' label, as an end period of the task.";

    public String name;
    public String from;
    public String to;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgumentException(NO_ARGUMENTS_ERROR);
        } else if (!Parser.containsExactOneLabel(args, "/from")) {
            throw new InvalidCommandArgumentException(FROM_TO_NUMBER_ERROR);
        } else if (!Parser.containsExactOneLabel(args, "/to")) {
            throw new InvalidCommandArgumentException(FROM_TO_NUMBER_ERROR);
        } else if (Parser.matches(args, "/from(.*)")) {
            throw new InvalidCommandArgumentException(NAME_EMPTY_ERROR);
        } else if (Parser.matches(args, "(.+)\\s/from\\s/to(.*)")) {
            throw new InvalidCommandArgumentException(FROM_EMPTY_ERROR);
        } else if (Parser.matches(args, "(.+)\\s/from\\s(.+)\\s/to")) {
            throw new InvalidCommandArgumentException(TO_EMPTY_ERROR);
        }

        String[] parsedArgs = Parser.parseArguments(args, "(.+)\\s/from\\s(.+)\\s/to\\s(.+)");
        name = parsedArgs[0];
        from = parsedArgs[1];
        to = parsedArgs[2];

        if (name.isEmpty()) {
            throw new InvalidCommandArgumentException(NAME_EMPTY_ERROR);
        } else if (from.isEmpty()) {
            throw new InvalidCommandArgumentException(FROM_EMPTY_ERROR);
        } else if (to.isEmpty()) {
            throw new InvalidCommandArgumentException(TO_EMPTY_ERROR);
        }
    }

    @Override
    protected String getArgumentErrorDetail() {
        return "Usage: event ((name)) /from ((start)) /to ((end))";
    }

    @Override
    public void run() {
        Nuke.addEvent(name, from, to);
    }
}
