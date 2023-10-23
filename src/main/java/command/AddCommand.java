package command;

/**
 * general command features with user raw command and
 */
public abstract class AddCommand extends Command {
    protected String command;

    /**
     * initialize the inherited isExit field as false
     * @param cmd represents raw user Command
     */
    public AddCommand(String cmd){
        super(false);
        this.command = cmd;
    }
}
