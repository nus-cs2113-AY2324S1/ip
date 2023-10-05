package duke.task;

import org.json.JSONObject;

/**
 *  Represents a task with a deadline
 */
public class Events extends Task{
    private String from;
    private String end;

    /**
     * Creates a new Events task with the name, start and end time and its done status
     *
     * @param taskName Name of task
     * @param from Start time
     * @param end End time
     * @param isDone Boolean representing whether task is completed
     */
    public Events(String taskName, String from, String end, Boolean isDone) {
        super(taskName, isDone);
        this.from = from;
        this.end = end;
    }

    /**
     * Updates the Start and End time
     *
     * @param newFrom New Start time
     * @param newEnd New End time
     */
    public void updateDate(String newFrom, String newEnd) {
        this.from = newFrom;
        this.end = newEnd;
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.end + ")";
    }

    /**
     * Converts the Events task to a JSON object.
     *
     * @return A JSON object representing the task.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject jo = new JSONObject();
        jo.put("taskType", "E");
        jo.put("isDone", this.getIsDone());
        jo.put("taskName", this.getTaskName());
        jo.put("from", this.from);
        jo.put("end", this.end);
        return jo;
    }
}
