package duke.tasks;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    private String fromTime;
    private String toTime;

    /**
     * Constructs an Event object with the given task name, start time, and end time.
     *
     * @param taskName The name of the task.
     * @param fromTime The start time of the task.
     * @param toTime The end time of the task.
     */
    public Event(String taskName, String fromTime, String toTime){
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Returns the start time of the Event object.
     *
     * @return The start time of the Event object.
     */
    public String getFromTime(){
        return this.fromTime;
    }

    /**
     * Sets the start time of the Event object.
     *
     * @param fromTime The new start time of the Event object.
     */
    public void setFromTime(String fromTime){
        this.fromTime = fromTime;
    }

    /**
     * Returns the end time of the Event object.
     *
     * @return The end time of the Event object.
     */
    public String getToTime(){
        return this.toTime;
    }

    /**
     * Sets the end time of the Event object.
     *
     * @param toTime The new end time of the Event object.
     */
    public void setToTime(String toTime){
        this.toTime = toTime;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    public String toString(){
        if(this.getIsDone()){
            return "[E][X] " + this.getTaskName() + " (" + this.fromTime + " to " + this.toTime + ")";
        }else{
            return "[E][ ] " + this.getTaskName() + " (" + this.fromTime + " to " + this.toTime + ")";
        }
    }
}