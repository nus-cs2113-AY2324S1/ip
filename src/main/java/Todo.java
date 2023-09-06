import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static Todo fromString(String userInput) throws ParseException {
        Pattern pattern = Pattern.compile("todo (.+)");
        Matcher matcher = pattern.matcher(userInput);

        matcher.find();

        if (matcher.matches() && matcher.groupCount() == 1) {
            String description = matcher.group(1);
            return new Todo(description);
        } else {
            throw new ParseException("\tInvalid todo format.", 0);
        }
    }
}
