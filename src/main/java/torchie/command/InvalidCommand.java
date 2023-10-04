package torchie.command;

public class InvalidCommand extends Command{

    public InvalidCommand() {
    }

    @Override
    public void handleCommand() {
        System.out.println("\neeTry again! Make sure command and format of command is correct!");
    }
}
