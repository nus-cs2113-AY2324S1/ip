package torchie.exception;

public class InvalidIndexException extends TorchieException{

    @Override
    public void showExceptionMessage() {
        System.out.println("Make sure task number is present AND valid");

    }
}
