package bob.parser;

import bob.BobException;
import bob.commands.UnmarkCommand;

public class UnmarkCommandParser implements Parser<UnmarkCommand> {
    public UnmarkCommand parse(String input) throws BobException {
        try {
            int markIdx = Integer.parseInt(input) - 1;
            return new UnmarkCommand(markIdx);
        } catch (NumberFormatException e) {
            throw new BobException("Unable to parse input into int.");
        }

    }
}
