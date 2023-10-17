package task;
/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isCompleted;

    /**
     * Creates a new Task object
     *
     * @param description The details of the task
     * @param isCompleted The status of the task
     */
    public Task(String description, boolean isCompleted) {
        setDescription(description);
        setCompleted(isCompleted);
    }
    /**
     * Get the type of task
     *
     * @return A string letter to represent the task
     */
    public String getType() {
        return "";
    }
    /**
     * Get the description of the task
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Set the description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Get the completion status of the task
     *
     * @return true for complete and false for otherwise
     */
    public boolean isCompleted() {
        return isCompleted;
    }
    /**
     * Set the completion status of the task
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    /**
     * Get the excess information beyond description and completion status
     * @return excess information
     */
    public String getExcess(){
        return "";
    }
    /**
     * Get all the information of the task
     *
     * @return The proper string output for the whole detail of the task
     */
    public String getStatus(){
        String status = " ";
        if(this.isCompleted()){
            status = "X";
        }
        return "[" + status + "] " + getDescription();
    }
}
