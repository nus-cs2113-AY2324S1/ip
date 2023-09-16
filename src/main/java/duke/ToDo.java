package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    @Override
    public String toString(){
        return String.format("[%s]%s", this.taskType, super.toString());
    }
}