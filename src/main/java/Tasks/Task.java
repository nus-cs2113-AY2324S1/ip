package Tasks;

/**
 * The Task class represents a basic task with a description and completion status.
 * It serves as the superclass for various types of tasks (Todo, Deadline, Event).
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected char type;
    protected static int numTasks = 0;

    /**
     * Returns the completion status icon for the task.
     *
     * @return The completion status icon ("X" for completed, " " for incomplete).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the total number of tasks created.
     *
     * @return The total number of tasks.
     */
    public static int getNumTasks() {
        return numTasks;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done and prints a completion message.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the type of the task (T for Todo, D for Deadline, E for Event).
     *
     * @param letter The type of task.
     */
    public void setType(char letter) {
        this.type = letter;
    }

    /**
     * Constructs a Task object with the provided description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = 'T';
        numTasks++;
    }

    /**
     * Unmarks a completed task and prints a message.
     */
    public void unmarkTask() {
        this.isDone = false;
        System.out.println("Nice! I've unmarked this task:\n\t  " + this);

    }

    /**
     * Returns a formatted string representation of the Task object.
     *
     * @return A string representing the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Prints a number of tasks.
     */
    public void printNewTask() {
        System.out.print("Got it. I've added this task:\n" + this + "\nNow you have " + getNumTasks());
        if (getNumTasks() > 1) {
            System.out.println(" tasks in the list.");
        } else {
            System.out.println(" task in the list.");
        }
    }
    /**
     * Prints the task that is going to be deleted.
     */
    public void printDeleteTask() {
        System.out.println("_____________________________________________________");
        int num_tasks = getNumTasks() - 1;
        System.out.print("Noted. I have removed this task:\n  " + this + "\nNow you have " + (getNumTasks() - 1));
        if (num_tasks > 1) {
            System.out.println(" tasks in the list.");
        } else {
            System.out.println(" task in the list.");
        }
        System.out.println("_____________________________________________________");
    }
}
