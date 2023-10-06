package python.task;

/**
 * Represents a Todo object with a description
 */
public class Todo extends Task {

    final static private String TYPE_ICON = "[T]";

    /**
     * Constructs <code>Todo</code> object
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon of todo
     *
     * @return Returns the type icon of todo
     */
    @Override
    public String getTypeIcon() {
        return TYPE_ICON;
    }
}
