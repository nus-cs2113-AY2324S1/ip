package eggybyte.ip.data.exception;

/**
 * Any excption will be throw in this type, which contains information about
 * this exception and the possible solution.
 */
public class TipsException extends Exception {
    public String error, tips;

    /**
     * Creating a new Tips Exception.
     */
    public TipsException(String error, String tips) {
        this.error = error;
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "Error: " + error
                + "\nTips: " + tips;
    }
}
