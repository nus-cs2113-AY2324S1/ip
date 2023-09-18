package elvis.task;

public class ToDo extends Task {
    private final char taskType = 'T';

    public ToDo(String description, int isDoneFromFile) {
        super(description, isDoneFromFile);
    }

    @Override
    public char getTaskType() {
        return taskType;
    }
}
