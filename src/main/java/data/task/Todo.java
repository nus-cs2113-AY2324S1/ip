package data.task;

/**
 * Represents a Todo in the TaskList.
 */
public class Todo extends Task{
    public Todo (String description) {
        super(description);
        isMarked = false;
    }

    public Todo (String description, int setMark) {
        super(description, setMark);
    }

    @Override
    public String getDetails() {
        return "[T]" + super.getDetails();
    }
}
