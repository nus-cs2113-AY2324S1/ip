package Duchess;


/** Class to handle commands and to return exit conditions */
public class CommandHandler {
    
    /** Exit flag to return to main program */
    private static int willExit = 0;

    /** Constructor class to be declared. */
    public CommandHandler(){
    }
    
    
    /**
    * Echoes input message as default command.
    * If exit command is parsed, return flag to trigger exit condition.
    *
    * @param command Command to be handled.
    * @method 
    * @return Flag to initiate exit of program.
    * @see Message echoed in console.
    */
    public int ParseCommand(String command) {
        switch (command) {
            default:
                String echoOutString = DefaultStrings.splittingLine + "\t "
                        + command + "\n" + DefaultStrings.splittingLine;
                System.out.println(echoOutString);
                willExit = 0;

            case Constants.endCommand:
                System.out.println(DefaultStrings.endString);
                willExit = 1;

        }
        return willExit;
    }   
}
