package duke.task;

import org.json.JSONObject;

public class Events extends Task{
    private String from;
    private String end;

    public Events(String taskName, String from, String end, Boolean isDone) {
        super(taskName, isDone);
        this.from = from;
        this.end = end;
    }

    public void UpdateDate(String newFrom, String newEnd) {
        this.from = newFrom;
        this.end = newEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.end + ")";
    }

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
