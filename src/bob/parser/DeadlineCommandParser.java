package bob.parser;

import bob.BobException;
import bob.commands.DeadlineCommand;
import bob.deadline.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommandParser implements Parser<DeadlineCommand> {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<description>.+) (?<by>/by)(?<deadline>[^/]+)");

    @Override
    public DeadlineCommand parse(String input) throws BobException {
        final Matcher matcher = COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            String missing = "";

            int byIdx = input.indexOf("/by");
            if (byIdx == -1) {
                missing += "The /by of a deadline must be specified";
            }

            // Extract task description and deadline from user input
            if (byIdx == 0 || input.isEmpty()) {
                missing += missing.isEmpty() ? "" : "\n\t";
                missing += "The description of a deadline cannot be empty";
            }

            int deadlineIdx = byIdx+ "/by ".length();
            if (deadlineIdx >= input.length()) {
                missing += missing.isEmpty() ? "" : "\n\t";
                missing += "The /by of a deadline cannot be empty";
            }

            throw new BobException(missing);
        }

        final String description = matcher.group("description").trim();

        try {
            final LocalDate deadline = LocalDate.parse(matcher.group("deadline").trim());
            Deadline newDeadline = new Deadline(description, deadline);
            return new DeadlineCommand(newDeadline);
        } catch (DateTimeParseException e) {
            throw new BobException("Date is in an incorrect format.");
        }
    }
}
