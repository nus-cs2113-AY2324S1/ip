package dawson.command;

import dawson.Dawson;

public class Echo extends Command {

    private String input;

    public Echo(String input) {
        this.input = input;
    }

    @Override
    public void execute() {
        Dawson.printText(input);
    }
    
}
