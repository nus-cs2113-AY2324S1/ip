public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if(description.isBlank()){
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone(){
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }
}
