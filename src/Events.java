public class Events extends Task {
    public char taskType = 'E';
    public Events(String description) {
        super(description);

    }

    @Override
    public char getTaskType() {
        return taskType;
    }
}
