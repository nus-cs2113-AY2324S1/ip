package duke.task;

import org.json.JSONObject;

/**
 *  Represents a task with a deadline
 */
public class Deadlines extends Task{
    private String dueDate;

    /**
     * Creates a new Deadline task with its name, dueDate and whether it is done
     *
     * @param taskName Name of task
     * @param dueDate Due date of task
     * @param isDone Boolean that showed whether task is completed
     */
    public Deadlines(String taskName, String dueDate, Boolean isDone) {
        super(taskName, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Updates the due date of a deadline task
     *
     * @param newDueDate The new due date
     */
    public void UpdateDueDate(String newDueDate) {
        this.dueDate = newDueDate;
    }


    /**
     * Returns a string representation of the Deadlines task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }

    /**
     * Converts the Deadlines task to a JSON object.
     *
     * @return A JSON object representing the task.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject jo = new JSONObject();
        jo.put("taskType", "D");
        jo.put("isDone", this.getIsDone());
        jo.put("taskName", this.getTaskName());
        jo.put("dueDate", this.dueDate);
        return jo;
    }
}
