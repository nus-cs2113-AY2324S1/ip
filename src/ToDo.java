public class ToDo extends Task {
    public char taskType = 'T';
    public ToDo(String description) {
        super(description);
    }

    @Override
    public char getTaskType() {
        return taskType;
    }
}
