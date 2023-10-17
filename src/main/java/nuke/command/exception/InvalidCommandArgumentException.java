package nuke.command.exception;

public class InvalidCommandArgumentException extends CommandException {
    public String reason;
    public String correctUsage;

    public InvalidCommandArgumentException(String reason, String correctUsage) {
        this.reason = reason;
        this.correctUsage = correctUsage;
    }
}
