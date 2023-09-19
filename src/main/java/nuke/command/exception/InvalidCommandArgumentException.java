package nuke.command.exception;

public class InvalidCommandArgumentException extends CommandException {
    public String reason;

    public InvalidCommandArgumentException(String reason) {
        this.reason = reason;
    }
}
