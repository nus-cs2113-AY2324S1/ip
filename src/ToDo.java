public class ToDo extends Task {
    private final char taskType = 'T';

    public ToDo(String description) {
        super(description);
    }

    @Override
    public char getTaskType() {
        return taskType;
    }
}
