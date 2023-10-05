package duke.task;

import org.json.JSONObject;

/**
 *  Represents a task type "ToDos"
 */
public class ToDos extends Task{

    /**
     * Creates a new ToDos task with its name and the status on whether it is completed
     *
     * @param toDoName Name of task
     * @param isDone Boolean representing whether task is completed
     */
    public ToDos(String toDoName, Boolean isDone) {
        super(toDoName, isDone);
    }

    /**
     * Returns a string representation of the ToDo.
     *
     * @return A string representation of the ToDO.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the ToDos task to a JSON object.
     *
     * @return A JSON object representing the task.
     */
    @Override
    public JSONObject toJSONObject() {
        JSONObject jo = new JSONObject();
        jo.put("taskType", "T");
        jo.put("isDone", this.getIsDone());
        jo.put("taskName", this.getTaskName());
        return jo;
    }
}
