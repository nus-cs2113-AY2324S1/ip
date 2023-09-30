package tasks;

/**
 * Represents a 'Deadline' task in the Duke application.
 * 
 * @see Task
 */
public class Deadline extends Task{
    protected String by;
    
    /*
     * Initializes a new Deadline with the given description and deadline.
     * 
     * @param description The description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("description cannot be empty");
        }
        this.by = by;
    }

    /*
     * Returns the deadline of the deadline.
     * 
     * @return The deadline of the deadline.
     */
    public String getBy() {
        return this.by;
    }

    /*
     * Converts the Deadline task object to a data representation for saving to a file.
     * 
     * @return The data representation of the Deadline task.
     */
    public static Task dataToTask(String taskData) {
        int firstSplitIndex = taskData.indexOf("|");
        int secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
        boolean isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
        String desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
        String by = taskData.substring(secondSplitIndex + 2);
        Deadline newDeadline =  new Deadline(desc, by);
        newDeadline.isDone = isDone;
        return newDeadline;
    }
    
    /*
     * Converts the Deadline task object to a data representation for saving to a file.
     * 
     * @return The data representation of the Deadline task.
     */
    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "D | " + done + " | " + this.description + " | " + this.by;
    }

    /*
     * Returns the string representation of the Deadline task.
     * 
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
