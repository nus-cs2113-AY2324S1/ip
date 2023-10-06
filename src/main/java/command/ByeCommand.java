package command;

import message.text;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public void executeCommand() {
        text.printByeMessage();
    }
}
