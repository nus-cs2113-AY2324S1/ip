package Command;

import Storage.TaskList;

public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "bye";
    @Override
    public void execute(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Thank you for using Soccat, hope to see you again soon!");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }
}
