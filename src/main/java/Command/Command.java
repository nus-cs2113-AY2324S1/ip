package Command;
import Task.TaskList;
import java.util.Scanner;
import Exception.FrankException;

public abstract class Command {
    protected String[] commands = null;
    protected Scanner input = new Scanner(System.in);
    public Command(){}
    public Command(String command) {
        this.commands = command.split(" ");
    }

    public abstract void execute(TaskList tasks) throws FrankException;
}