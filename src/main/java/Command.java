public abstract class Command {

    public abstract void applyArguments(String args) throws InvalidCommandArgumentException;

    public void handleArgumentError(InvalidCommandArgumentException e) {
        Ui.printCommandError(e.reason, getArgumentErrorDetail());
    }

    protected abstract String getArgumentErrorDetail();

    public abstract void run();

    public static void handleTypeError(InvalidCommandTypeException e) {
        String desc = String.format("There is no command called '%s'.", e.type);
        String detail = "Existing command: bye, list, mark, unmark, todo, deadline, event";
        Ui.printCommandError(desc, detail);
    }
}
