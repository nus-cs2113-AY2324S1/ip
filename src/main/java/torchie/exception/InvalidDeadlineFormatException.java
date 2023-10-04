package torchie.exception;

public class InvalidDeadlineFormatException extends TorchieException{
    @Override
    public void showExceptionMessage() {
        System.out.println("Invalid format! Correct format for deadline: \"deadline read /by Aug 1st\"");
    }

}
