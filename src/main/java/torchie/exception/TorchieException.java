package torchie.exception;

public abstract class TorchieException extends Exception{

    public TorchieException() {
    }
    public TorchieException(String message) {
        System.out.println("Error >.<: " + message);
    }

    public abstract void showExceptionMessage();

}
