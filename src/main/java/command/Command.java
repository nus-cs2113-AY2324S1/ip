package command;

import java.io.IOException;

/**
 * parent class representing all user instructions
 */
public abstract class Command {

    /**
     * use isExit to keep tract if the user want to quit
     */
    private boolean isExit;

    public Command(boolean isExit){
        this.isExit = isExit;
    }

    public boolean isExit(){
        return isExit;
    }

    /**
     *
     * @throws IOException file input output error in case file cannot be found
     */
    public abstract void executeCommand() throws IOException;
}

