package fredbot.error;

public class FredBotDeleteErrorException extends FredBotException {
    @Override
    public String getMessage() {
        return "This task does not exist!";
    }

}
