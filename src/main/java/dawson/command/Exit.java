package dawson.command;

import dawson.Dawson;

public class Exit extends Command {

    @Override
    public void execute() {
        String exitString = " Bye. Hope to see you again soon!";
        Dawson.printText(exitString);
    }
    
}
