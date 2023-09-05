public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick symbol once done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }

//    // Formats the task information for listing tasks with a status icon
//    public String formatForList(int index) {
//        return index + ". [" + getStatusIcon() + "]" + " " + description;
//    }
//
//    // Formats the task information for marking tasks as done with a status icon
//    public String formatForMark(int index) {
//        return " [" + getStatusIcon() + "]" + " " + description;
//    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
