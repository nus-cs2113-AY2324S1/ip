public class Todo extends Task {
    protected boolean isDone;
    protected String type;

    public Todo(String description) {
        super(description);
        isDone = false;
        type = "T";
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return "[" + type + "][" + status + "] " + description;
    }
}