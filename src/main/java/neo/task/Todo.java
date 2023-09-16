package neo.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    @Override
    public String formatTask() {
        return "T / " + super.formatTask();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
