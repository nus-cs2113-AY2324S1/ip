import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.getDescription(), this.by);
    }

    public static Deadline fromString(String userInput) throws ParseException {
        Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches() && matcher.groupCount() == 2) {
            String description = matcher.group(1);
            String by = matcher.group(2);
            return new Deadline(description, by);
        } else {
            throw new ParseException("\tInvalid deadline format.", 0);
        }
    }
}
