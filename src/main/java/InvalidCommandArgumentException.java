public class InvalidCommandArgumentException extends NukeException {
    public String reason;

    public InvalidCommandArgumentException(String reason) {
        this.reason = reason;
    }
}
