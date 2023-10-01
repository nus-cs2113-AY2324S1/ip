package command;

import exception.FrankException;
import task.TaskList;
import java.io.IOException;
import java.util.Scanner;

/**
 * Clears everything from the task list
 * This will also clear storage as well as it saves immediately after
 */
public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws FrankException, IOException {
        System.out.println("This will remove all tasks! Are you sure? + " +
                "Type Y to confirm, another key to cancel. ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("y")){
            tasks.clearTasks();
        }
        // else nothing
    }

}