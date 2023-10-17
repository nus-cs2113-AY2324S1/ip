package fredbot.error;

public class FredBotMarkErrorException extends FredBotException{
    @Override
    public String getMessage() {
        return "This task does not exist!";
    }

}
