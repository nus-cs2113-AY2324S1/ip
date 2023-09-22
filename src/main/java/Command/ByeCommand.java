package Command;
import Task.TaskList;
public class ByeCommand extends Command {
    final String SOLIDLINE = "\n------------------------------------------------------------------------------------------------------------------------------\n";
    @Override
    public void execute(TaskList tasks) {
        System.out.println(SOLIDLINE + "\nGoodbye User, zai jian yong ze, sampai jumpa lagi user \n" + SOLIDLINE);
        // Future implementation: add username
        System.exit(0);
    }
}
