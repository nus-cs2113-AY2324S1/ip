package torchie.command;

public class InvalidCommand extends Command{

    public InvalidCommand() {
    }

    @Override
    public void handleCommand() {
        System.out.println("\nTry again! Make sure command and format of command is correct!");
    }
}
