package luke.user;

/**
 * The LukeException Class represents custom exceptions specific to the Luke application.
 * It extends the Exception class.
 */
public class LukeException extends Exception {
    @Override
    public String getMessage() {
        return "LukeException has occurred.";
    }
}