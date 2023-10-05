package alice.exceptions;

public class InvalidCommandException extends AliceException {
    public InvalidCommandException() {
        System.out.println("    NOOOOOOOOoooooooo... Your command is WRONG!\ndo try again!\n");
    }
}