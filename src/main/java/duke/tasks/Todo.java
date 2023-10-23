package duke.tasks;

public class Todo extends Task{

    /**
     * A subclass extends Task that handles the command "todo"
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Override the toString() method of the superclass,
     * so it can return its own description
     * @return the description of todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
