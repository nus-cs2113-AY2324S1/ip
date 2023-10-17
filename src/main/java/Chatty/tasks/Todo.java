/**
 * The Todo class represents a task with a item to do.
 */
package Chatty.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    /**
     * Gets the description for the deadline task
     */
    @Override
    public String getDescription() {
        return "[T][" + getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String saveFormat() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + description;
    }
}
