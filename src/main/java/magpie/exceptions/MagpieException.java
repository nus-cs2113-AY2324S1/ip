package magpie.exceptions;
public class MagpieException extends Exception {

    private String errorMessage;

    public MagpieException(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {

        return errorMessage;
    }
}
