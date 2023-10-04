package magpie.task;

/**
 * Represents a <b>Todo</b> Task with <code>description</code>
 * <i>Example: return book</i>
 */
public class Todo extends Task {

    /**
     * Constructs <code>description</code> and <code>isDone</code>.
     */
    public Todo(String description) {

        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextToWrite() {
        return "T | " + parseBooleanToInt(isDone) + " | " + description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

}
