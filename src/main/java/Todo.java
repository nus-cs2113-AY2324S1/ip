public class Todo extends Task {
    public Todo(String description) {
        super(description, false);
    }

    @Override
    public String getStatus() {
        return "[T]" + super.getStatus();
    }

}
