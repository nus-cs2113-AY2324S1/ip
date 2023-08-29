public class Todo {
    protected String description;
    protected boolean isDone;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
    public String getDescription() {
        return description;
    }
}
