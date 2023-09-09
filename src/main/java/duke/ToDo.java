package duke;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
        super.taskType = "todo";
    }
}
