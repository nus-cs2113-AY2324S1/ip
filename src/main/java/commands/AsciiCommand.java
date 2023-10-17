package commands;

import ascii.AsciiArt;
import main.Parser;
/**
 * Represents an ascii Command to turn on and off ascii art
 */
public class AsciiCommand extends Command {
    @Override
    public void execute(String statement, Parser processor) throws IllegalArgumentException {
        if (statement.equalsIgnoreCase("true")){
            AsciiArt.setEnabled(true);
        } else if (statement.equalsIgnoreCase("false")){
            AsciiArt.setEnabled(false);
        } else {
            throw new IllegalArgumentException("ascii only accepts true/false arguments!");
        }
    }
}
