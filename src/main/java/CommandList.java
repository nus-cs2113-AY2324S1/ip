public class CommandList extends Command {
    private static final String TOO_MANY_ARGUMENTS_ERROR =
            "Command 'list' should have no argument.";

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgumentException(TOO_MANY_ARGUMENTS_ERROR);
        }
    }

    @Override
    public void run() {
        Nuke.listTask();
    }
}
