package Exceptions;

public class KenMissingTaskException extends KenException {
    public KenMissingTaskException(String message) {
        super("Sweetie, that's not on the Barbie agenda!");
    }
}
