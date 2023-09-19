package Chatty.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return "[T][" + getStatusIcon() + "] " + super.getDescription();
    }
}
