package torchie.exception;

public class TorchieException extends Exception{

    public TorchieException() {
    }
    public TorchieException(String message) {
        System.out.println("Error >.<: " + message);
    }

}
