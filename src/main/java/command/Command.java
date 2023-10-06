package command;

import java.io.IOException;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit){
        this.isExit = isExit;
    }

    public boolean isExit(){
        return isExit;
    }

    public abstract void executeCommand() throws IOException;
}

