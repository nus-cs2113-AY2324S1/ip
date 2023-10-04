package torchie.command;

public class InvalidCommand extends Command{

    public InvalidCommand() {
    }

    @Override
    public void handleCommand() {
        System.out.println("Invalid Command!");
    }
}
