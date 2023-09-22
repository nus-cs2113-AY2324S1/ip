package Task;

public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFile() {
        return "T," + (isCompleted() ? "1" : "0") + "," + getName();
    }
}
