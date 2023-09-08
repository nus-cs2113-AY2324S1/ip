public class Events extends Task{
    protected Task task;
    protected String startTime;
    protected String endTime;

    public Events(String description, String startTime, String endTime) {
        super(description + " (from: " + startTime + " to: " + endTime + ")");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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
    public void printTask() {
        System.out.print("[E]");
        super.printTask();
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask();
    }
}
