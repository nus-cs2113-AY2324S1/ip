package tasks;


/**
 * The task object contains the subclasses ToDo, Deadline and Event, and has the members
 * name and completion status that is shared among all subclasses.
 */
public class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
}
