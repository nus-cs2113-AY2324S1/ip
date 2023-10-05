package python.task;

public class Todo extends Task {

    final static public String TYPE_ICON = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return TYPE_ICON;
    }
}
