public class CommandBye extends Command {
    private static final String TOO_MANY_ARGUMENTS_ERROR =
            "Command 'bye' should have no argument.";

    @Override
    public void applyArguments(String args) throws InvalidCommandArgumentException {
        if (!args.isEmpty()) {
            throw new InvalidCommandArgumentException(TOO_MANY_ARGUMENTS_ERROR);
        }
    }

    @Override
    protected String getArgumentErrorDetail() {
        return "Usage: bye";
    }

    @Override
    public void run() {
        Nuke.quit();
    }
}
