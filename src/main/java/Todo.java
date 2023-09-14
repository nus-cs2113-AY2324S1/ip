public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getFormattedTask() {
        return "[T] " + super.getFormattedTask();
    }
}
