package tasks;

import exceptions.TaskEmptyDescriptionException;

/**
 * Represents a event task that starts and ends at a specific time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) throws TaskEmptyDescriptionException {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Print the Event task to the user.
     */
    @Override
    public void printTask() {
        System.out.println("\t[E]" + getCompletedString() + getDescription()
            + " (from: " + getStartTime() + ", to: " + getEndTime() + ")");
    }
}
