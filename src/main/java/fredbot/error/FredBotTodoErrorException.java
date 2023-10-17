package fredbot.error;

public class FredBotTodoErrorException extends FredBotException {
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

}
