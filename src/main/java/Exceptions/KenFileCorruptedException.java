package Exceptions;

/**
 * A custom exception class for handling file corruption issues in the Barbie-themed task manager.
 * This exception is thrown when a file is corrupted.
 */
public class KenFileCorruptedException extends KenException {

    /**
     * Constructs a new instance of the KenFileCorruptedException class with the specified error message.
     *
     * @param message The error message that describes the file corruption issue.
     */
    public KenFileCorruptedException(String message) {
        super(message);
    }
}
