package alice.exceptions;

public class InvalidCommandException extends AliceException {
    private static final String ERROR_MESSAGE = "    NOOOOOOOOoooooooo... Your command is WRONG!\n    do try again!\n";

    public void getErrorMessage() {
        System.out.println(ERROR_MESSAGE);
    }
}