package bob.parser;

import bob.BobException;
import bob.commands.Command;

public interface Parser<T extends Command> {

    T parse(String input) throws BobException;
}
