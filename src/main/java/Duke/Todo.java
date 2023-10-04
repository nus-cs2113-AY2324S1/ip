package Duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String getDescription() {
        return "[" + type + "]" + super.getDescription();
    }
}
