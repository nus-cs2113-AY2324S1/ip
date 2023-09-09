package commands;

import main.ResponseProcessor;
import task.Deadline;
import task.Todo;

public class TodoCommand extends Command {
    @Override
    public void execute(String statement, ResponseProcessor processor) throws IllegalArgumentException {
        if ("".equalsIgnoreCase(statement)) {
            throw new IllegalArgumentException ("uwu master please put in the task name! (.>ෆ<.)");
        } else {
            processor.taskList.add(new Todo(statement));
            System.out.println("added: " + processor.taskList.get(processor.taskList.size() - 1).getStatus());
            System.out.println("Now you have " + processor.taskList.size() + " tasks in the list masta (´UωU`)");
        }
    }
}
