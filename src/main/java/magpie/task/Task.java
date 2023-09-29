package magpie.task;

/**
 * Represents a <b>Task</b> containing <code>description</code> and completion status with <code>isDone</code>
 */
public class Task {

    protected String description;
    protected boolean isDone;

    protected String textToWrite;

    /**
     * Constructor for <code>description</code> and <code>isDone</code>.
     *
     * @param description description of Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon "X" if task is marked. <br>
     * If the task is not marked, a blank " " is returned.
     *
     * @return Status Icon "X" or " ".
     */
    public String getStatusIcon() {

        String done = " ";

        if (isDone) {
            done = "X";
        }
        return done;
    }

    /**
     * Sets isDone to indicate Task is marked or not marked.
     *
     * @param done Boolean value to indicate marked or unmarked.
     */
    public void setDone(boolean done) {

        isDone = done;
    }

    /**
     * Parse <code>true</code> to Integer value <code>1</code>.
     * Parse <code>false</code> to Integer value <code>0</code>.
     *
     * @param input Boolean value to be parsed into an Integer.
     * @return Parsed Integer value.
     */
    public static int parseBooleanToInt(boolean input) {

        if (input){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Returns formatted line containing details of Task object to be written to data file.
     *
     * @return Line to be written to data file.
     */
    public String getTextToWrite() {
        return this.textToWrite;
    }

    /**
     * Returns formatted line containing Task Status Icon and Description to be printed to User.
     *
     * @return Line to be printed.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }


}
