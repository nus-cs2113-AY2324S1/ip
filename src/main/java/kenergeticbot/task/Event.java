package kenergeticbot.task;

public class Event extends Task {
    protected String dateTime;
    public Event(String description, String taskType, String dateTime) {
        super(description, taskType);
        this.dateTime = dateTime;
    }

    public String toString() {
        return taskType + super.toString() + dateTime;
    }
}
