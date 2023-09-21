package duke.task;

import org.json.JSONObject;

public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName, boolean isDone) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        if(!this.isDone) {
            return ("[ ] " + this.taskName);
        } else {
            return ("[X] " + this.taskName);
        }
    }


    public boolean getIsDone() {
        return this.isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public JSONObject toJSONObject() {
        return null;
    }
}
