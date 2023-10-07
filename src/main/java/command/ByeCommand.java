package command;

import message.Text;

import java.io.IOException;
import fileIO.FileIO;


public class ByeCommand extends Command {
    /**
     * only bye message will change isExit field to true
     */
    public ByeCommand() {
        super(true);
    }

    @Override
    public void executeCommand() {
            Text.printByeMessage();
    }
}
