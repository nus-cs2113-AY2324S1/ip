package dawson.task;

public class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String encode() {
        String isDoneString = isDone ? "1" : "0";
        return String.format("T | %s | %s", isDoneString, description);
    }

}