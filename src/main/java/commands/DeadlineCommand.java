package commands;

import main.ResponseProcessor;
import task.Deadline;

public class DeadlineCommand extends Command {


    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        int indexBy = getIndex(statement,"/by");
        String description = statement.substring(0, indexBy);
        String by = statement.substring(indexBy + 3);
        isValidTask(description);
        if (!by.isBlank()) {
            processor.taskList.add(new Deadline(description, by));
            System.out.println("added: " + processor.taskList.get(processor.taskList.size() - 1).getStatus());
            System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta (´UωU`)");
        } else {
            throw new IllegalArgumentException ("Your /by is empty uwu! (つT . T)つ");
        }
    }

}
