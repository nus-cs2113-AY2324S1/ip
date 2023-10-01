package command;
import task.TaskList;
import java.io.IOException;
import java.util.Scanner;
import exception.FrankException;
import utility.Ui;

public abstract class Command {
    protected String[] commands = null;
    protected Scanner input = new Scanner(System.in);
    public Command(){}

    /**
     * Splits up an unparsed command into separate words
     * @param command String command from the user
     */
    public Command(String command) {
        this.commands = command.split(" ");
    }

    /**
     * Will execute a series of actions in Frank based on the specific command
     *
     * @param tasks TaskList of Current Tasks. Used for interacting with the current list
     * @param ui Current User Interface. Used for user input and output
     * @throws FrankException Unique Exceptions
     * @throws IOException Input Exceptions
     */
    public abstract void execute(TaskList tasks, Ui ui) throws FrankException, IOException;
}