package chat0pt.tasks;

public class Task {
    private final String task;
    private boolean isMarked;

    /**
     * Constructor for each task containing task name and whether it has been marked
     * @param task Task Name
     */
    public Task(String task) {
        this.task = task;
        this.isMarked = false;
    }

    public String getTask() {
        return task;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (isMarked) {
            toReturn += "[X] ";
        } else {
            toReturn += "[ ] ";
        }
        return toReturn + task;
    }


    public String toFile() {
        String toReturn = "";
        if (isMarked) {
            toReturn += "true#";
        } else {
            toReturn += "false#";
        }
        return toReturn + task;
    }
}
