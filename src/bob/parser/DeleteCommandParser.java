package bob.parser;

import bob.BobException;
import bob.commands.DeleteCommand;
import bob.commands.MarkCommand;

public class DeleteCommandParser implements Parser<DeleteCommand>{

    @Override
    public DeleteCommand parse(String input) throws BobException {
        try {
            int deleteIdx = Integer.parseInt(input) - 1;
            return new DeleteCommand(deleteIdx);
        } catch (NumberFormatException e) {
            throw new BobException("Unable to parse input into int.");
        }
    }
}
