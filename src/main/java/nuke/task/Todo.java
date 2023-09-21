package nuke.task;

/**
 * Represents a task to do.
 */
public class Todo extends Task {

    /**
     * Constructs a task to do with the name.
     *
     * @param name name of the task to do.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public static final String TYPE = "T";
}
