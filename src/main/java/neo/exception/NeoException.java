package neo.exception;

import neo.type.ErrorType;

public class NeoException extends Exception {
    protected String description;
    protected ErrorType type;


    public NeoException(String description, ErrorType type) {
        this.description = description;
        this.type = type;
    }

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
        }
    }
}