package dawson.commands;

public abstract class Command {
    private static final String EXIT_COMMAND = "bye";
    
    public static Command commandChecker(String commandText, String input) {
        if (commandText.equals(EXIT_COMMAND)) {
            return new Exit();
        } 
        return new Echo(input);
    };

    public abstract void execute();
}
