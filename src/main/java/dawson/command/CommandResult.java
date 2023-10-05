package dawson.command;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    
    /** The feedback message to be shown to the user. Contains a description of the execution result */
    private final String[] message;

    public CommandResult(String... message) {
        this.message = message;
    }

    public String[] getMessageStrings() {
        return this.message;
    }

}
