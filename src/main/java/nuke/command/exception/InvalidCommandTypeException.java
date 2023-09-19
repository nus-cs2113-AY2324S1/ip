package nuke.command.exception;

public class InvalidCommandTypeException extends CommandException {
    public String type;

    public InvalidCommandTypeException(String type) {
        this.type = type;
    }
}
