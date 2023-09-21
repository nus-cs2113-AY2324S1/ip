package duke.task;

import org.json.JSONObject;

public class ToDos extends Task{

    public ToDos(String toDoName, Boolean isDone) {
        super(toDoName, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jo = new JSONObject();
        jo.put("taskType", "T");
        jo.put("isDone", this.getIsDone());
        jo.put("taskName", this.getTaskName());
        return jo;
    }
}
