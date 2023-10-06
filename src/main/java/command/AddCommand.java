package command;

/**
 * general command features with user raw command and
 */
public abstract class AddCommand extends Command {
    protected String command;

    public AddCommand(String cmd){
        super(false);
        this.command = cmd;
    }
}
