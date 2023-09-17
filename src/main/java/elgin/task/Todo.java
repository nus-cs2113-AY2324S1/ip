package elgin.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        type = "T";
    }

    public Todo(String description, boolean isDone) {
        super(description);
        type = "T";
        setIsDone(isDone);
    }

}
