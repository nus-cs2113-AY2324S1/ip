package dawson.command;

import dawson.Dawson;

public class InvalidCommand extends Command {

    @Override
    public void execute() {
        Dawson.printText("Invalid command! Please enter a valid command");
    }
    
}
