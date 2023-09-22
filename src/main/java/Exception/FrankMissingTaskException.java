package Exception;

public class FrankMissingTaskException extends FrankException {
    public FrankMissingTaskException() {
        super("You haven't filled in a Task!");
    }
}
