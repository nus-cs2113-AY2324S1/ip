import org.json.JSONObject;

public class Deadlines extends Task{
    private String dueDate;

    public Deadlines(String taskName, String dueDate, Boolean isDone) {
        super(taskName, isDone);
        this.dueDate = dueDate;
    }


    public void UpdateDueDate(String newDueDate) {
        this.dueDate = newDueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }

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
