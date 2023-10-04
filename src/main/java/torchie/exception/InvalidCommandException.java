package torchie.exception;

public class InvalidCommandException extends TorchieException{

    @Override
    public void showExceptionMessage() {
        System.out.println("Invalid command! Make sure the command (first word) is valid");
    }
}
