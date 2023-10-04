package jerry.commands;

import jerry.task.TaskList;
import jerry.task.Task;
import jerry.common.Messages;

import java.util.List;

public abstract class Command {

    protected TaskList taskList;
    private int targetIndex = -1;

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    public void setData(TaskList tasklist) {
        this.taskList = tasklist;
    }

    public abstract CommandResult execute();

    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return taskList.getTaskByIndex(getTargetIndex());
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of tasks.
     *
     * @param tasksDisplayed used to generate summary
     * @return summary message for tasks displayed
     */
    public static String getMessageForTaskListShownSummary(List<Task> tasksDisplayed) {
        return String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, tasksDisplayed.size());
    }

}
