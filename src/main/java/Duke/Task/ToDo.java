package Duke.Task;

public class ToDo extends Task {
//    String taskType;
    public ToDo(String description) {
        super(description);
        taskType = "todo";
        symbol = "T";
    }


    @Override
    public String toString() {
        return "\t[T]" + super.toString();
    }
}
