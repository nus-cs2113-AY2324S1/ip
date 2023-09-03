public class Deadlines extends Task {
    public char taskType = 'D';
    public Deadlines(String description) {
        super(description);

    }

    @Override
    public char getTaskType() {
        return taskType;
    }
}
