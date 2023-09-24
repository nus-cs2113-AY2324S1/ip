package Soccat;

/**
 * Represents a todo event.
 * */

public class Todo extends Task{

    public static final String TASK_CHAR = "T";

    public Todo(String name) {
        super(name);
    }

    /**
     * Converts a todo object to a string,
     * delimited by the token <code>SPLIT_CHAR</code>
     *
     * @return string of parsed todo.
     * */
    public String toTokenString() {
        if (super.getDone()) {
            return TASK_CHAR + Task.SPLIT_CHAR + Task.DONE_CHAR + Task.SPLIT_CHAR + super.getName() + "\n";
        } else {
            return TASK_CHAR + Task.SPLIT_CHAR + Task.NOT_DONE_CHAR + Task.SPLIT_CHAR + super.getName() + "\n";
        }
    }

    @Override
    public String toString() {
        return "[" + TASK_CHAR + "]" + super.toString();
    }
}
