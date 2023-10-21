package command;

import message.Text;

import java.io.IOException;
import fileIO.FileIO;


public class ByeCommand extends Command {

    /**
     * only bye message can change isExit field to true
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * only print bye message when running bye execution
     */
    @Override
    public void executeCommand() {
            Text.printByeMessage();
    }
}
