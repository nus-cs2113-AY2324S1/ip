package command;

import message.Text;


public class HelpCommand extends Command {

    public HelpCommand(){
        super(false);
    }

    /**
     * use to print help message only
     */
    @Override
    public void executeCommand() {
        Text.printHelpMessage();
    }

}
