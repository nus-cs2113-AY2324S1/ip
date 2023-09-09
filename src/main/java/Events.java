public class Events extends Task{
    protected String startTime;
    protected String endTime;

    public Events(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")");
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask(this.toString());
    }
}
