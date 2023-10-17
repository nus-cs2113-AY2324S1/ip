package Exceptions;

/**
 * A custom exception class for handling file reading issues in the Barbie-themed task manager.
 * This exception is thrown when there is a problem reading from the file.
 */
public class KenReadFromFileException extends KenException {

    /**
     * Constructs a new instance of the KenReadFromFileException class with a predefined error message.
     * The error message indicates that there's an issue with reading from the file.
     */
    public KenReadFromFileException() {
        super("Oops, darling! Reading from the file is like, so not happening right now!");
    }
}
