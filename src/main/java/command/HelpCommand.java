package command;

import message.Text;


public class HelpCommand extends Command {

    public HelpCommand(){
        super(false);
    }

    @Override
    public void executeCommand() {
        Text.printHelpMessage();
    }

}
