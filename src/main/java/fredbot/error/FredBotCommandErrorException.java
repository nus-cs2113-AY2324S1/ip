package fredbot.error;

public class FredBotCommandErrorException extends FredBotException  {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}


