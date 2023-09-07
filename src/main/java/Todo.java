public class Todo extends Task {
    private boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }
}
