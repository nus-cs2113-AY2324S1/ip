package eggybyte.ip.data.task;

/**
 * A child class of Task that has a new variable 'isDone' to show whether the
 * task is done.
 */
public class Todo extends Task {
    protected final String type;
    public boolean isDone;

    /**
     * Creating a new Task.
     */
    public Todo(String description) {
        this(description, "T");
    }

    /**
     * Creating a new Task.
     * It's used for child classes to avoid implemented duplicated function.
     */
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