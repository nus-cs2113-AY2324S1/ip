public class InvalidCommandTypeException extends NukeException {
    public String type;

    public InvalidCommandTypeException(String type) {
        this.type = type;
    }
}
