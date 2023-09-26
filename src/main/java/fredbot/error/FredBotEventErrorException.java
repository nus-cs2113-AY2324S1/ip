package fredbot.error;

public class FredBotEventErrorException extends FredBotException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a event cannot be empty or date format is wrong!";
    }
}
