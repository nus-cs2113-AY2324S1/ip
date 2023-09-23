package eggybyte.ip.data.exception;

public class TipsException extends Exception {
    public String error, tips;

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
