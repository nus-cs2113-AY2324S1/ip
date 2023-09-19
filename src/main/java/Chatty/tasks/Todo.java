package Chatty.tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return "[T][" + getStatusIcon() + "] " + super.getDescription();
    }

    @Override
    public String saveFormat() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + description;
    }
}
