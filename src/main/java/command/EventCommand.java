package command;

import task.Event;
import task.TaskList;
import exception.FrankException;

public class EventCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws FrankException {
        System.out.println("Boleh! What is the event?");
        String description = input.nextLine();
        System.out.println("When does it start?");
        String startDate = input.nextLine();
        System.out.println("When does it end?");
        String endDate = input.nextLine();
        if(description.isEmpty() || startDate.isEmpty() || endDate.isEmpty()){
            throw new FrankException("Brough you forgot to fill in something!");
        }
        tasks.addTask(new Event(description, startDate, endDate));
    }
}