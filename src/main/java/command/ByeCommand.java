package command;

import message.Text;

import java.io.IOException;
import fileIO.FileIO;


public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    @Override
    public void executeCommand() {

            Text.printByeMessage();
            FileIO.backupTaskFile();

    }
}
