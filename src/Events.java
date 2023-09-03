public class Events extends Task {
    private char taskType = 'E';
    private String startTime;
    private String endTime;
    public Events(String description, String startTime, String endTime) {
        super(description);
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
