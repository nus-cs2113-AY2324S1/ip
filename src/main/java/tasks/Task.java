package tasks;


/**
 * The task object contains the subclasses ToDo, Deadline and Event, and has the members
 * name and completion status that is shared among all subclasses.
 */
public class Task {

    private String name;

    private String taskType;
    private boolean isDone;

    public Task(String name, String taskType) {
        this.name = name;
        this.taskType = taskType;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        String taskTypeString = "[" + taskType + "]";
        String completedStatusString = isDone ? "[X]" : "[ ]";
        return taskTypeString + completedStatusString + " " + name;
    }

    public String toSaveString() {
        String completedStatusString = isDone ? "Y" : "N";
        return taskType + "|" + completedStatusString + "|" + name;
    }
}
