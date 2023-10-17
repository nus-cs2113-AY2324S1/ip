package torchie.exception;

public class InvalidDateTimeException extends TorchieException {
    @Override
    public void showExceptionMessage() {
        System.out.println("Invalid datetime format! Correct format: \"2007-12-03 1015\"");
    }
}
