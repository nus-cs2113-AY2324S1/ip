package commands;

import main.ResponseProcessor;
import task.Deadline;

public class DeadlineCommand extends Command {


    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        if (statement.contains("/by")) {
            int indexBy = statement.indexOf("/by");
            String description = statement.substring(0, indexBy);
            String by = statement.substring(indexBy + 3);
            if (!by.isEmpty() && !description.isEmpty()) {
                processor.taskList.add(new Deadline(description, by));
                System.out.println("added: " + processor.taskList.get(processor.taskList.size() - 1).getStatus());
                System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta (´UωU`)");
            } else {
                throw new IllegalArgumentException ("Some of your inputs are empty uwu! (つT . T)つ");
            }
        }
        else {
            throw new IllegalArgumentException ("Insufficient commands for deadline masta! (.>ෆ<.)");
        }
    }

}
