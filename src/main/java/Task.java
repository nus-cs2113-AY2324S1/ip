public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }



}
