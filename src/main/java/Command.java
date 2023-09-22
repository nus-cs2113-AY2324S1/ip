import java.util.Scanner;

public abstract class Command {
    protected String[] commands = null;
    protected Scanner input = new Scanner(System.in);
    public Command(){}
    public Command(String command) {
        this.commands = command.split(" ");
    }

    public abstract void execute(TaskList tasks) throws FrankException;
}