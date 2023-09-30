package command;

import task.TaskList;
import java.util.Scanner;
import exception.FrankException;
import task.Todo;

public class TodoCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws FrankException {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your todo?" );
        String description = input.nextLine();
        if(description.isEmpty()) {
            throw new FrankException("Brough this description is missing");
        }
        tasks.addTask(new Todo(description));
    }
}
