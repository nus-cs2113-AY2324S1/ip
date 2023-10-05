package parser;

import exceptions.InvalidCommandException;
import tasks.TaskList;

public class ValidityParser{
    TaskList tasks;

    public ValidityParser(TaskList tasks) {
        this.tasks = tasks;
    }

    public int getValidIndex(int taskId) throws InvalidCommandException{
        boolean taskIdInRange = true;
        if (taskId >= tasks.size() || taskId < 0) {
            taskIdInRange = false;
        }

        if (!taskIdInRange){
            throw new InvalidCommandException();
        }
        return taskId;
    }
}
