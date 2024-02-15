package mudmud.exception;

/**
 * Indicates an error caused by invalid command being inputted.
 */
public class DukeCommandException extends Exception{

    /**
     * Sends out an error message that the command is invalid.
     *
     * @param command The invalid command.
     */
    public void handleDukeCommandException(String command) {
        System.out.println("\tSorry I don't quite understand what your command is.");
        System.out.println("\tPlease a valid command.");
        System.out.println("\tThe command you tried to input is: " + command);
    }
}
