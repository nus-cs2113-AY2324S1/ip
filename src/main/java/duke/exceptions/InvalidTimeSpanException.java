package duke.exceptions;

/**
 * Represents an exception when the start time of an event is after the end time.
 */
public class InvalidTimeSpanException extends Exception {

    /**
     * Notifies user when the start time of an event is after the end time.
     */
    public void printErrorMessage() {
        System.out.println("The start time of an event cannot be after the end time!");
    }
}
