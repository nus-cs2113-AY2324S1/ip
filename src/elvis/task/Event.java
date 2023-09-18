package elvis.task;

public class Event extends Task {
    private final char taskType = 'E';
    private String startTime;
    private String endTime;

    public Event(String description, int isDoneFromFile, String startTime, String endTime) {
        super(description, isDoneFromFile);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public String getStartTime() {
        return startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }
}
