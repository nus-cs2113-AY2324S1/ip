package task;

public class Todo extends Task {
    /**
     * Constructor for Todo Task
     * @param description Description of the Task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
                           return "[T]" + super.toString();
        }
}
