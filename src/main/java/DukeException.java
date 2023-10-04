/**
 * Custom Exception for Duke
 */
public class DukeException extends Exception{
    String errorMessage;
    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Format error message for printing to command line interface.
     * @return Return the error message of the specific exception in String format.
     */
    @Override
    public String toString() {
        return errorMessage;
    }

}
