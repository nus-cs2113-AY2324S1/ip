package bob.parser;

import bob.BobException;
import bob.commands.MarkCommand;

public class MarkCommandParser implements Parser<MarkCommand> {
    public MarkCommand parse(String input) throws BobException {
        try {
            int markIdx = Integer.parseInt(input) - 1;
            return new MarkCommand(markIdx);
        } catch (NumberFormatException e) {
            throw new BobException("Unable to parse input into int.");
        }

    }
}
