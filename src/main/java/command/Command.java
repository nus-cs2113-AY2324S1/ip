package command;
import task.TaskList;
import java.io.IOException;
import java.util.Scanner;
import exception.FrankException;

public abstract class Command {
    protected String[] commands = null;
    protected Scanner input = new Scanner(System.in);
    public Command(){}
    public Command(String command) {
        this.commands = command.split(" ");
    }

    public abstract void execute(TaskList tasks) throws FrankException, IOException;
}