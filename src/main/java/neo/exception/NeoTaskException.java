package neo.exception;

import neo.type.ErrorType;

/**
 * A form of throwable that indicates conditions specific to Neo chatbot that can be caught.
 */
public class NeoTaskException extends Exception {
    protected String description;
    protected ErrorType type;

    /**
     * Constructs exception with parameters to indicate the type of exception.
     *
     * @param description Describes which part of task caused the error
     * @param type The type of error that was caused
     */
    public NeoTaskException(String description, ErrorType type) {
        this.description = description;
        this.type = type;
    }

    public NeoTaskException() {
        this.description = null;
        this.type = null;
    }

    /**
     * Prints the exception and recommended actions to the user.
     */
    public void printException() {
        switch (this.type) {
        case EMPTY:
            if (description.equals("description")) {
                System.out.println("OOPS!!! The description cannot be left empty.");
            } else {
                System.out.println("OOPS!!! The description of " + description + " cannot be left empty.");
            }
            break;
        case FORMAT:
            System.out.println("OOPS!!! Please check that " + description + " is included in the command.");
            break;
        case MISUSE:
            if (description.equals("/by")) {
                System.out.println("OOPS!!! Did you mean Deadline?");
            }
            if (description.equals("/from")) {
                System.out.println("OOPS!!! Did you mean Event?");
            }
            break;
        default:
            System.out.println("Unable to print exception.");
            break;
        }
    }
}