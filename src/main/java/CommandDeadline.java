public class CommandDeadline extends Command {
    private static final String NO_ARGUMENTS_ERROR =
            "Command 'deadline' should have arguments.";
    private static final String BY_NUMBER_ERROR =
            "Command 'deadline' should have one '/by' label for the deadline.";
    private static final String NAME_EMPTY_ERROR =
            "Command 'deadline' should have a string as a name of the task.";
    private static final String BY_EMPTY_ERROR =
            "Command 'deadline' should have a string as a deadline of the task.";

    public String name;
    public String by;

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (args.isEmpty()) {
            throw new InvalidCommandArgumentException(NO_ARGUMENTS_ERROR);
        } else if (!Parser.containsExactOneLabel(args, "/by")) {
            throw new InvalidCommandArgumentException(BY_NUMBER_ERROR);
        } else if (Parser.matches(args, "/by(.*)")) {
            throw new InvalidCommandArgumentException(NAME_EMPTY_ERROR);
        } else if (Parser.matches(args, "(.+)\\s/by")) {
            throw new InvalidCommandArgumentException(BY_EMPTY_ERROR);
        }

        String[] parsedArgs = Parser.parseArguments(args, "(.+)\\s/by\\s(.+)");
        name = parsedArgs[0];
        by = parsedArgs[1];
    }

    @Override
    public void run() {
        Nuke.addDeadline(name, by);
    }
}
