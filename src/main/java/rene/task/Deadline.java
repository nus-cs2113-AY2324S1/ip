package rene.task;
public class Deadline extends Task {
    private String dueTime;

    public Deadline(String description, String dueTime){
        super(description, TaskType.DEADLINE);
        this.dueTime = dueTime;
    }

    @Override
    public String getTaskTiming() {
        return "(by: " + dueTime + ")";
    }

}
