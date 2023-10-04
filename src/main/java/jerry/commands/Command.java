package jerry.commands;

import static jerry.ui.TextUi.DISPLAYED_INDEX_OFFSET;

import jerry.task.TaskList;
import jerry.task.Task;
import jerry.common.Messages;

import java.util.List;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected TaskList taskList;
    protected List<Task> relevantTasks;
    private int targetIndex = -1;

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    public void setData(TaskList tasklist, List<Task> relevantTasks) {
        this.taskList = tasklist;
        this.relevantTasks = relevantTasks;
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute();

    /**
     * Find the task corresponding to the selected index in the last shown list
     * 
     * @return the appropriate task
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return relevantTasks.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
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
