package kenergeticbot.task;

/**
 * Represents an Event Task
 * Contains Task DateTime, Task Description and Task Type = [T]
 */
public class Event extends Task {
    protected String dateTime;
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        this.taskType = "[E]";
    }

    public String toString() {
        return taskType + super.toString() + dateTime;
    }

    public String printTaskToSave() {
        String task = taskType.replace("[", "").replace("]","");
        return  task + super.printTaskToSave() + " | " + dateTime + "\n";
    }
}
