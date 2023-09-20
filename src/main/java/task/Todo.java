package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, false);
    }

    @Override
    public String getType() {
        return "T";
    }
    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}
