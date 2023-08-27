public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {

        String done = " ";

        if (isDone == true) {
            done = "X";
        }
        return done;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
