package duke.exception;

public class DukeCommandException extends Exception{

    public void handleDukeCommandException(String command) {
        System.out.println("\tSorry I don't quite understand what your command is.");
        System.out.println("\tPlease a valid command.");
        System.out.println("\tThe command you tried to input is: " + command);
    }
}
