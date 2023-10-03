package MySun.data.task;
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, int setMark) {
        this.description = description;
        if (setMark == 1) {
            isDone = true;
        } else if (setMark == 0) {
            isDone = false;
        }
    }

    public String getOnlyDescription() {
        return description;
    }

    public String getDescription() {
        String mark;
        if (isDone()){
            mark = "X";
        } else {
            mark = " ";
        }
        return "[" + mark + "] " + description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

}
