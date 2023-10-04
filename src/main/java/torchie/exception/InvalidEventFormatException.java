package torchie.exception;

public class InvalidEventFormatException extends TorchieException {
    @Override
    public void showExceptionMessage() {
        System.out.println("Invalid format! Correct format for event: \"event meeting /from 2pm /to 4pm\"");
    }
}