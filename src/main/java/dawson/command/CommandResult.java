package dawson.command;

public class CommandResult {
    
    private final String[] message;

    public CommandResult(String... message) {
        this.message = message;
    }

    public String[] getMessageStrings() {
        return this.message;
    }

}
