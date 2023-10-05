package torchie.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean b) {
        this.isDone = b;
    }

    /**
     * Used to check if Task was marked or not
     *
     * @return String that reflects status of Task
     *
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done torchie.task with X
    }

    public void printTask(String s) {
        System.out.println(s);
    }

    /**
     * Used to display Task object in a user-friendly way
     *
     * @return String that shows a user-friendly version of Task object
     *
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Mark task and print a line to tell users that task was marked successfully
     *
     */
    public void markTask() {
        setIsDone(true);
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        printTask(this.toString());
    }

    /**
     * Unmark task and print a line to tell users that task was unmarked successfully
     *
     */
    public void unmarkTask() {
        setIsDone(false);
        System.out.println("Ok, I've marked this task as not done yet: ");
        printTask(this.toString());
    }

    /**
     * Print a line to tell users that task was added successfully
     *
     */
    public void announceTaskAdd() {
        System.out.println("Got it. I've added this task: ");
        System.out.print("\t");
    }

    /**
     * Print a line to tell users that task was deleted successfully
     *
     */
    public void announceTaskDelete() {
        System.out.println("Noted. I've removed this task: ");
        System.out.print("\t");
    }

    /**
     * Change Deadline task to a format that will be used to store in file
     *
     * @return String raw format to be saved in file
     *
     */
    public String toFileFormat() {
        return (getStatusIcon() + " | " + getDescription());
    }

}