public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick symbol once done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String formatForList(int index) {
        return index + ". [" + getStatusIcon() + "]" + " " + description;
    }

    public String formatForMark(int index) {
        return " [" + getStatusIcon() + "]" + " " + description;
    }
}
