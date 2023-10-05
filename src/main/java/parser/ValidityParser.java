package parser;

import tasks.TaskList;

public class ValidityParser{
    TaskList tasks;

    public ValidityParser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * To check if the task Id is within the range of the number of tasks.
     * @param taskId
     * @return
     */
    public boolean taskIdInRange(int taskId) {
        if (taskId >= tasks.size() || taskId < 0) {
            return false;
        }
        return true;
    }
}
