package MySun.data.task;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo (String description, int setMark) {
        super(description, setMark);
    }

    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }
}