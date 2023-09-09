package commands;

import main.ResponseProcessor;
import task.Deadline;
import task.Event;

public class EventCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        if (statement.contains("/from") && statement.contains("/to")) {
            int indexFrom = statement.indexOf("/from");
            int indexTo = statement.indexOf("/to");
            String description = statement.substring(0, indexFrom);
            String from = statement.substring(indexFrom + 5, indexTo);
            String to = statement.substring(indexTo + 3);
            if (!from.isEmpty() && !to.isEmpty() && !description.isEmpty()) {
                processor.taskList.add(new Event(description, from, to));
                System.out.println("added: " + processor.taskList.get(processor.taskList.size() - 1).getStatus());
                System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta (´UωU`)");
            } else {
                throw new IllegalArgumentException ("Some of your inputs are empty uwu! (つT . T)つ");
            }
        } else {
            throw new IllegalArgumentException ("Insufficient commands for event masta! (.>ෆ<.)");
        }
    }
}
