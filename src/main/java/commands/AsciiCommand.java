package commands;

import ascii.AsciiArt;
import main.ResponseProcessor;

public class AsciiCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        if (statement.equalsIgnoreCase("true")){
            AsciiArt.setEnabled(true);
        } else if (statement.equalsIgnoreCase("false")){
            AsciiArt.setEnabled(false);
        } else {
            throw new IllegalArgumentException("ascii only accepts true/false arguments!");
        }
    }
}
