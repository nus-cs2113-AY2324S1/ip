package magpie.task;

public class Todo extends Task {

    public Todo(String description) {

        super(description);
    }

    public String getTextToWrite() {
        return "T | " + parseBooleanToInt(isDone) + " | " + description;
    }

    public String toString() {

        return "[T]" + super.toString();
    }

}
