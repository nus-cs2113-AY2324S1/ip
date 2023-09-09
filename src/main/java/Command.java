public abstract class Command {

    public abstract void applyArguments(String args) throws InvalidCommandArgumentException;

    public void handleArgumentError(InvalidCommandArgumentException e) {
        Ui.printCommandError(e.reason);
    }

    public abstract void run();

    public static void handleTypeError(InvalidCommandTypeException e) {
        String desc = String.format("I'm sorry, but I don't know what '%s' means.", e.type);
        Ui.printCommandError(desc);
    }
}
