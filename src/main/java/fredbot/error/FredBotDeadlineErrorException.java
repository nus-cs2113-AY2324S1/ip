package fredbot.error;

public class FredBotDeadlineErrorException extends FredBotException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
}
