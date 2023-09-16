package nuke.task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public static final String TYPE = "T";
}
