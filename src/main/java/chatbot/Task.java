package chatbot;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the description
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Get the status icon
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Mark task as done
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Mark task as undone
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}