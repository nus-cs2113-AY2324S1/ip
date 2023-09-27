package eggybyte.ip.data.task;

public class Todo extends Task {
    protected final String type;
    public boolean isDone;

    public Todo(String description) {
        this(description, "T");
    }

    public Todo(String description, String type) {
        super(description);
        this.type = type;
        isDone = false;
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