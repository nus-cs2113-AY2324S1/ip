package Duke;

import java.util.ArrayList;

/**
 * Represents a Task
 */
public class Task {
    
    protected String description;
    protected boolean isDone;
    private static int count = 0;
    protected String type;

    /**
     * Constructor to create a new Task with the given description
     * @param description - The description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    /**
     * Get the type of the Task
     * @return The type of the Task
     */
    public String getTaskType() {
        return type;
    }

    /**
     * Get the status of the Task in string format ("X" if done, " " if not done)
     * @return - The status of the Task
     */
    public String getTaskStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Get the description of the Task
     * @return The description of the Task
     */
    public String getDescriptionText() {
        return this.description;
    }

    /**
     * Get the complete Task description with the Task status
     * @return The complete Task description
     */
    public String getDescription() {
        return "[" + this.getTaskStatus() + "] " + this.description;
    }

    /**
     * Mark the Task done by setting its status to "X"
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
    * Unmark the Task by setting its status to " "
    */
    public void unMarkTask() {
        this.isDone = false;
    }

    /**
     * Get the total no. of Task created
     * @return The total no. of Task
     */
    public static int getTaskCount() {
        return count;
    }

    /**
     * Remove a Task from the taskList and reduce the Task count
     * @param taskList The list of Task
     * @param taskIndex The index of the Task to remove
     */
    public static void removeTask(ArrayList<Task> taskList, int taskIndex) {
        taskList.remove(taskIndex);
        count--;
    }
    
    /**
     * Get the start time of the Task (implemented in Event)
     * @return The start time of the Task
     */
    public String getStart() {
        return null;
    }

    /**
     * Get the end time of the Task (implemented in Event)
     * @return The end time of the Task
     */
    public String getEnd() {
        return null;
    }

    /**
     * Get the when informaton for the Task (implemented in Deadline)
     * @return The when information for the Task
     */
    public String getWhen() {
        return null;
    }

}
