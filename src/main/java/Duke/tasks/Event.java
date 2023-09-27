package duke.tasks;

/**
 * Represents a type of task that can be added into
 * the list, namely an event type task.
 */
public class Event extends Task{

    public Event(String newTask,String start,String end) {
        super(newTask);
        taskType = new String[1];
        taskType[0] = "E";
        startTime = start;
        endTime= end;
    }
}
