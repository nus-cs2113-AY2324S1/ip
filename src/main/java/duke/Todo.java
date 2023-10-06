package duke;
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return "T | " + doneStatus + " | " + description;
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
