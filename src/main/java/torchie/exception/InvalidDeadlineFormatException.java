package torchie.exception;

public class InvalidDeadlineFormatException extends TorchieException{
    @Override
    public void showExceptionMessage() {
        System.out.println("Invalid format! Correct format for deadline: \"deadline read /by yyyy-mm-ddTHH:mm\"");
    }

}
