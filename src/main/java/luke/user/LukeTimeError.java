package luke.user;

/**
 * The LukeTimeError Class represents custom exceptions specific to the LukeTime application.
 * It extends the Exception class.
 */
public class LukeTimeError extends Exception {
    @Override
    public String getMessage() {
        return "LukeTimeError";
    }
}