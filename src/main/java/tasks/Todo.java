package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String typeOfTask = "[T]";
        return typeOfTask + super.toString();
    }
}