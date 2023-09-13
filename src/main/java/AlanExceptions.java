public class AlanExceptions extends Exception {
    String errorMessage;
    public AlanExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
