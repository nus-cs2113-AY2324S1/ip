package lemon.task;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFile() {
        return "T" + super.toFile();
    }
}
