package Tasks;

import Exceptions.TaskEmptyDescriptionException;

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

    @Override
    public void printTask() {
        System.out.println("\t[E]" + getCompletedString() + getDescription() + " (from: " + getStartTime() + " to " + getEndTime() + ")");
    }
}
