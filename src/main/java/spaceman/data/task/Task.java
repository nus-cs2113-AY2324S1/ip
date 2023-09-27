package spaceman.data.task;

/**
 * Represents a Task in the TaskList.
 */
public class Task {
    protected boolean isMarked;
    protected String description;
    private static int count = 0;

    /**
     * Initializes the Task object.
     * @param description description of the task
     */
    public Task (String description) {
        this.description = description;
        count++;
    }

    /**
     * Initializes the Task object.
     * @param description description of the task
     * @param setMark status of the task
     */
    public Task (String description, int setMark) {
        this.description = description;
        if (setMark == 1) {
            isMarked = true;
        } else if (setMark == 0) {
            isMarked = false;
        }
        count++;
    }

    /**
     * Mark task as done.
     */
    public void markTask() {
        isMarked = true;
    }

    /**
     * Mark task as not done.
     */
    public void unMarkTask() {
        isMarked = false;
    }

    public boolean getTaskStatus() {
        return isMarked;
    }

    public String getDetails() {
        String mark;
        if (getTaskStatus()){
            mark = "X";
        } else {
            mark = " ";
        }
        return "[" + mark + "] " + description;
    }

    public String getDescription() {
        return description;
    }

    public static int getTaskCount() {
        return count;
    }

    /**
     * Decrease the task count by one when a task is deleted.
     */
    public static void decreaseTaskCountByOne() {
        count--;
    }

}
