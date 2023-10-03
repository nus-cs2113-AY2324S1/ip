package elgin.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor of Task.
     *
     * @param description Description of the Todo.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets task description.
     *
     * @return Description of the Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks/Unmarks the Task as done.
     *
     * @param isDone True if the task is completed, else False.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the isDone state of the task and represent
     * it as a symbol.
     *
     * @return "X" if task is completed, else return " ".
     */
    public String getIsDone() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the isDone state of Task as 1 or 0
     * for storing into file.
     *
     * @return 1 if isDone is true, else 0.
     */
    public String getIsDoneAsOneOrZero() {
        return (isDone ? "1" : "0");
    }

    /**
     * Gets type of task (Todo, Deadline, Event)
     *
     * @return Char representation of Task.
     */
    public String getType() {
        return type;
    }

    /**
     * Formats the string representation of Task to
     * show type, isDone statue and description.
     *
     * @return String of formatted Task.
     */
    public String toString() {
        return "[" + getType() + "][" + getIsDone() + "] " + getDescription();
    }
}
