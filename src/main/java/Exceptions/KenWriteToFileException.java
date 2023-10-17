package Exceptions;

/**
 * A custom exception class for handling file writing issues in the Barbie-themed task manager.
 * This exception is thrown when there is a problem writing data to the file.
 */
public class KenWriteToFileException extends KenException {

    /**
     * Constructs a new instance of the KenWriteToFileException class with a predefined error message.
     * The error message indicates that there's an issue with writing to the file.
     */
    public KenWriteToFileException() {
        super("Uh-oh, darling! Writing to the file is a total fashion fail!");
    }
}
