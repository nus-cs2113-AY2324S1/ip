package task;

/**
 * The most basic of all 3 sub-classes of Task, a Todo is a Task which only has a description.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String getCode() {
        return "T";
    }
}
